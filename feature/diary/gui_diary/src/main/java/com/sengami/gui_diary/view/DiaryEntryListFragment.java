package com.sengami.gui_diary.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.text.InputType;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.annimon.stream.Stream;
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
import com.sengami.gui_base.util.KeyboardUtil;
import com.sengami.gui_base.util.OnTextChangedListener;
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
    private List<DiaryEntry> diaryEntries = Collections.emptyList();
    private ErrorHandler errorHandler;
    private LoadingIndicator loadingIndicator;

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
        errorHandler = new ToastErrorHandler(context);
        loadingIndicator = new ViewVisibilityLoadingIndicator(binding.loadingWheelOverlay);
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
        this.diaryEntries = diaryEntries;
        updateListFilteredBySearchPhrase();
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
        return errorHandler;
    }

    @Override
    @NotNull
    public LoadingIndicator getLoadingIndicator() {
        return loadingIndicator;
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
        binding.searchEditText.addTextChangedListener(new OnTextChangedListener() {
            @Override
            protected void onTextChanged(@NotNull final String text) {
                updateListFilteredBySearchPhrase();
            }
        });
        onClick(binding.searchButton, this::onSearchButtonClicked);
    }

    private void updateListFilteredBySearchPhrase() {
        final List<DiaryEntry> filteredEntries = getEntriesFilteredBySearchPhrase();
        final List<DiaryEntryListElement> items = converter.convert(filteredEntries);
        adapter.replaceAll(items);
        binding.recyclerView.setAdapter(adapter);
    }

    private List<DiaryEntry> getEntriesFilteredBySearchPhrase() {
        final String searchPhrase = binding.searchEditText.getText().toString();
        if (searchPhrase.isEmpty()) {
            return diaryEntries;
        }

        return Stream
            .of(diaryEntries)
            .filter(entry -> entry.containsPhrase(searchPhrase))
            .toList();
    }

    private void onSearchButtonClicked() {
        if (binding.searchBar.getVisibility() != View.VISIBLE) {
            showSearchBar();
        } else {
            hideSearchBar();
        }
    }

    private void showSearchBar() {
        binding.searchButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getViewContext(), R.color.color_primary_dark)));
        binding.searchButton.setColorFilter(ContextCompat.getColor(getViewContext(), R.color.color_accent));
        binding.searchEditText.setEnabled(true);
        binding.searchEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        binding.searchBar.setVisibility(View.VISIBLE);
        KeyboardUtil.showKeyboard(getViewContext(), binding.searchEditText);
    }

    private void hideSearchBar() {
        binding.searchButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getViewContext(), R.color.color_accent)));
        binding.searchButton.setColorFilter(ContextCompat.getColor(getViewContext(), R.color.color_primary_dark));
        binding.searchEditText.setText("");
        binding.searchEditText.setEnabled(false);
        binding.searchEditText.setInputType(InputType.TYPE_NULL);
        binding.searchBar.setVisibility(View.GONE);
        KeyboardUtil.hideKeyboard(getViewContext(), binding.searchEditText);
    }
}