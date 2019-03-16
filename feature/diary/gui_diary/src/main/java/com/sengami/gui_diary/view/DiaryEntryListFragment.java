package com.sengami.gui_diary.view;

import android.content.Context;
import android.widget.Toast;

import com.sengami.domain_diary.contract.DiaryEntryListContract;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_diary.R;
import com.sengami.gui_diary.databinding.FragmentDiaryEntryListBinding;
import com.sengami.gui_diary.di.component.DaggerDiaryComponent;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class DiaryEntryListFragment extends BaseFragment<DiaryEntryListContract.Presenter, FragmentDiaryEntryListBinding> implements DiaryEntryListContract.View {

    private final BehaviorSubject<DiaryEntry> diaryEntryClickedTrigger = BehaviorSubject.create();

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
    @NotNull
    public Observable<DiaryEntry> getDiaryEntryClickedTrigger() {
        return diaryEntryClickedTrigger;
    }

    @Override
    public void showDiaryEntryList(@NotNull List<DiaryEntry> diaryEntryList) {
        Toast.makeText(getContext(), "asd", Toast.LENGTH_SHORT).show();
    }
}