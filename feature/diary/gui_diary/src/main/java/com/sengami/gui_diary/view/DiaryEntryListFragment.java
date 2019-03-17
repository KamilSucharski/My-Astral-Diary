package com.sengami.gui_diary.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sengami.domain_diary.contract.DiaryEntryListContract;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_diary.R;
import com.sengami.gui_diary.databinding.FragmentDiaryEntryListBinding;
import com.sengami.gui_diary.di.component.DaggerDiaryComponent;
import com.sengami.gui_diary.navigation.Extra;
import com.sengami.gui_diary.navigation.RequestCode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class DiaryEntryListFragment extends BaseFragment<DiaryEntryListContract.Presenter, FragmentDiaryEntryListBinding> implements DiaryEntryListContract.View {

    private final BehaviorSubject<DiaryEntry> diaryEntryClickedTrigger = BehaviorSubject.create();
    private final BehaviorSubject<Boolean> addNewDiaryEntryClickedTrigger = BehaviorSubject.create();

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
        DaggerDiaryComponent.builder()
            .contextModule(new ContextModule(context))
            .build()
            .inject(this);
    }

    @Override
    protected void init(@NotNull final Context context) {
        super.init(context);
        setupListeners();
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
        Toast.makeText(getContext(), "asd", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDiaryEntryComposerScreen(@NotNull final DiaryEntry diaryEntry) {
        final Intent intent = new Intent(getContext(), DiaryEntryComposerActivity.class);
        intent.putExtra(Extra.DIARY_ENTRY.name(), diaryEntry);
        startActivityForResult(intent, RequestCode.COMPOSE_DIARY_ENTRY.code());
    }

    @Override
    public void onActivityResult(final int requestCode,
                                 final int resultCode,
                                 @Nullable final Intent data) {
        if (requestCode == RequestCode.COMPOSE_DIARY_ENTRY.code() && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getContext(), "powrot ok", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupListeners() {
        onClick(binding.addEntryButton, () -> addNewDiaryEntryClickedTrigger.onNext(true));
    }
}