package com.sengami.gui_diary.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sengami.context.di.module.ContextModule;
import com.sengami.domain_base.error.ErrorHandler;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.LoadingIndicator;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_diary.contract.DiaryEntryListContract;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.error_handler.implementation.ToastErrorHandler;
import com.sengami.gui_base.BaseFragment;
import com.sengami.gui_diary.R;
import com.sengami.gui_diary.databinding.FragmentDiaryEntryListBinding;
import com.sengami.gui_diary.di.component.DaggerDiaryEntryListComponent;
import com.sengami.gui_diary.navigation.Extra;
import com.sengami.gui_diary.navigation.RequestCode;
import com.sengami.gui_diary.view.list.adapter.DiaryEntryListAdapter;
import com.sengami.gui_diary.view.list.converter.DiaryEntryListElementConverter;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;
import com.sengami.recycler_view_adapter.converter.ElementConverter;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;
import com.sengami.util_loading_indicator.implementation.ViewVisibilityLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

import static com.sengami.clicks.Clicks.onClick;

public final class DiaryEntryListFragment
    extends BaseFragment<DiaryEntryListContract.Presenter, FragmentDiaryEntryListBinding>
    implements DiaryEntryListContract.View, WithErrorHandler, WithLoadingIndicator {

    private final BehaviorSubject<Boolean> refreshListTrigger = BehaviorSubject.create();
    private final BehaviorSubject<DiaryEntry> diaryEntryClickedTrigger = BehaviorSubject.create();
    private final BehaviorSubject<Boolean> addNewDiaryEntryClickedTrigger = BehaviorSubject.create();
    private final ElementConverter<List<DiaryEntry>, DiaryEntryListElement> converter = new DiaryEntryListElementConverter();
    private BaseAdapter<DiaryEntryListElement, DiaryEntryListElementType> adapter;

    @Inject
    @Override
    protected void injectPresenter(@NotNull final DiaryEntryListContract.Presenter presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_diary_entry_list;
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerDiaryEntryListComponent.builder()
            .contextModule(new ContextModule(context))
            .withErrorHandlerModule(new WithErrorHandlerModule(this))
            .withLoadingIndicatorModule(new WithLoadingIndicatorModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected void init(@NotNull final Context context) {
        super.init(context);
        setupListeners();
        setupList(context);
        refreshListTrigger.onNext(true);
    }

    @Override
    @NotNull
    public Observable<Boolean> getRefreshListTrigger() {
        return refreshListTrigger;
    }

    @Override
    @NotNull
    public Observable<DiaryEntry> getDiaryEntryClickedTrigger() {
        return diaryEntryClickedTrigger;
    }

    @Override
    @NotNull
    public Observable<Boolean> getAddNewDiaryClickedEntryTrigger() {
        return addNewDiaryEntryClickedTrigger;
    }

    @Override
    public void showDiaryEntryList(@NotNull final List<DiaryEntry> diaryEntryList) {
        final List<DiaryEntryListElement> items = converter.convert(diaryEntryList);
        adapter.replaceAll(items);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void navigateToDiaryEntryComposerScreen(@NotNull final DiaryEntry diaryEntry) {
        final Intent intent = new Intent(getContext(), DiaryEntryComposerActivity.class);
        intent.putExtra(Extra.DIARY_ENTRY.name(), diaryEntry);
        startActivityForResult(intent, RequestCode.COMPOSE_DIARY_ENTRY.code());
    }

    @Override
    @NotNull
    public ErrorHandler getErrorHandler() {
        return new ToastErrorHandler(getContext());
    }

    @Override
    @NotNull
    public LoadingIndicator getLoadingIndicator() {
        return new ViewVisibilityLoadingIndicator(binding.loadingWheelOverlay);
    }

    @Override
    public void onActivityResult(final int requestCode,
                                 final int resultCode,
                                 @Nullable final Intent data) {
        if (requestCode == RequestCode.COMPOSE_DIARY_ENTRY.code() && resultCode == Activity.RESULT_OK) {
            refreshListTrigger.onNext(true);
        }
    }

    private void setupListeners() {
        onClick(binding.addEntryButton, () -> addNewDiaryEntryClickedTrigger.onNext(true));
    }

    private void setupList(@NotNull final Context context) {
        adapter = new DiaryEntryListAdapter(context, diaryEntryClickedTrigger);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }
}