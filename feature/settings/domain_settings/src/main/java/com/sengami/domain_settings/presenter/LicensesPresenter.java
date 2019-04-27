package com.sengami.domain_settings.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_settings.view.LicensesView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class LicensesPresenter extends BasePresenter<LicensesView> {

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final LicensesView view) {
        return Collections.emptyList();
    }
}