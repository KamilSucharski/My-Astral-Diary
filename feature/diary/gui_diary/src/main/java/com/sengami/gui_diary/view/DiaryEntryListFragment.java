package com.sengami.gui_diary.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.android_operation.implementation.ToastErrorHandler;
import com.sengami.android_operation.implementation.ViewVisibilityLoadingIndicator;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.error.ErrorHandler;
import com.sengami.domain_base.operation.loading.LoadingIndicator;
import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_diary.view.DiaryEntryListView;
import com.sengami.gui_base.navigation.Extra;
import com.sengami.gui_base.navigation.FlowCoordinator;
import com.sengami.gui_base.navigation.FlowCoordinatorProvider;
import com.sengami.gui_base.navigation.RequestCode;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_diary.R;
import com.sengami.gui_diary.databinding.FragmentDiaryEntryListBinding;
import com.sengami.gui_diary.di.component.DaggerDiaryEntryListComponent;
import com.sengami.gui_diary.view.list.adapter.DiaryEntryListAdapter;
import com.sengami.gui_diary.view.list.converter.DiaryEntryListElementConverter;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import static com.sengami.clicks.Clicks.onClick;

public final class DiaryEntryListFragment
    extends BaseFragment<Presenter<DiaryEntryListView>, FragmentDiaryEntryListBinding>
    implements DiaryEntryListView {

    private final Subject<Boolean> refreshListTrigger = PublishSubject.create();
    private final Subject<DiaryEntry> diaryEntryClickedTrigger = PublishSubject.create();
    private final Subject<Boolean> addNewDiaryEntryClickedTrigger = PublishSubject.create();
    private final DiaryEntryListElementConverter converter = new DiaryEntryListElementConverter();
    private BaseAdapter<DiaryEntryListElement, DiaryEntryListElementType> adapter;
    private List<DiaryEntry> cachedDiaryEntryList = Collections.emptyList();

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<DiaryEntryListView> presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_diary_entry_list;
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerDiaryEntryListComponent
            .builder()
            .withErrorHandlerModule(new WithErrorHandlerModule(this))
            .withLoadingIndicatorModule(new WithLoadingIndicatorModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected void init(@NotNull final Context context) {
        super.init(context);
        setupList(context);
        setupListeners();
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
    public void showDiaryEntries(@NotNull final List<DiaryEntry> diaryEntries) {
        cachedDiaryEntryList = diaryEntries;
        updateListWithCachedEntries();
    }

    @Override
    public void navigateToDiaryEntryComposerScreen(@NotNull final DiaryEntry diaryEntry) {
        final FlowCoordinator flowCoordinator = FlowCoordinatorProvider.provide();
        final Intent intent = flowCoordinator.diaryEntryComposerActivityIntent(getViewContext());
        intent.putExtra(Extra.DIARY_ENTRY.name(), diaryEntry);
        startActivityForResult(intent, RequestCode.COMPOSE_DIARY_ENTRY.code());
    }

    @Override
    @NotNull
    public ErrorHandler getErrorHandler() {
        return new ToastErrorHandler(getViewContext());
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

    private void setupList(@NotNull final Context context) {
        adapter = new DiaryEntryListAdapter(diaryEntryClickedTrigger);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    private void setupListeners() {
        onClick(binding.addEntryButton, () -> addNewDiaryEntryClickedTrigger.onNext(true));
        binding.searchBar.searchEditText.addTextChangedListener(searchBarTextWatcher());
    }

    private TextWatcher searchBarTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s,
                                          final int start,
                                          final int count,
                                          final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s,
                                      final int start,
                                      final int before,
                                      final int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                converter.setDairyEntryTextFilter(s.toString().trim().toLowerCase());
                updateListWithCachedEntries();
            }
        };
    }

    private void updateListWithCachedEntries() {
        final List<DiaryEntryListElement> items = converter.convert(cachedDiaryEntryList);
        adapter.replaceAll(items);
        binding.recyclerView.setAdapter(adapter);
    }
}