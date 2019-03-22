package com.sengami.gui_statistics.view;

import android.content.Context;

import com.sengami.context.di.module.ContextModule;
import com.sengami.domain_base.error.ErrorHandler;
import com.sengami.domain_base.loading.LoadingIndicator;
import com.sengami.domain_statistics.presenter.StatisticsPresenter;
import com.sengami.domain_statistics.view.StatisticsView;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.error_handler.implementation.ToastErrorHandler;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.FragmentStatisticsBinding;
import com.sengami.gui_statistics.di.component.DaggerStatisticsComponent;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;
import com.sengami.util_loading_indicator.implementation.ViewVisibilityLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public final class StatisticsFragment
    extends BaseFragment<StatisticsPresenter, FragmentStatisticsBinding>
    implements StatisticsView {

    private ErrorHandler errorHandler;
    private LoadingIndicator loadingIndicator;

    @Inject
    @Override
    protected void injectPresenter(@NotNull final StatisticsPresenter presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerStatisticsComponent.builder()
            .contextModule(new ContextModule(context))
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
}