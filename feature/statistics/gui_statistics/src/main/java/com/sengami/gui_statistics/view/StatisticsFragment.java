package com.sengami.gui_statistics.view;

import android.content.Context;

import com.sengami.context.di.module.ContextModule;
import com.sengami.domain_base.error.ErrorHandler;
import com.sengami.domain_base.loading.LoadingIndicator;
import com.sengami.domain_base.model.Statistics;
import com.sengami.domain_statistics.presenter.StatisticsPresenter;
import com.sengami.domain_statistics.view.StatisticsView;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.error_handler.implementation.ToastErrorHandler;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.FragmentStatisticsBinding;
import com.sengami.gui_statistics.di.component.DaggerStatisticsComponent;
import com.sengami.gui_statistics.view.list.adapter.StatisticsListAdapter;
import com.sengami.gui_statistics.view.list.converter.StatisticsListElementConverter;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;
import com.sengami.recycler_view_adapter.converter.ElementConverter;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;
import com.sengami.util_loading_indicator.implementation.ViewVisibilityLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public final class StatisticsFragment
    extends BaseFragment<StatisticsPresenter, FragmentStatisticsBinding>
    implements StatisticsView {

    @NotNull
    private final Subject<Boolean> refreshStatisticsTrigger = PublishSubject.create();
    private ErrorHandler errorHandler;
    private LoadingIndicator loadingIndicator;
    private BaseAdapter<StatisticsListElement, StatisticsListElementType> adapter;
    private ElementConverter<Statistics, StatisticsListElement> converter;

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
        setupList(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshStatisticsTrigger.onNext(true);
    }

    @Override
    @NotNull
    public Observable<Boolean> getRefreshStatisticsTrigger() {
        return refreshStatisticsTrigger;
    }

    @Override
    public void showStatistics(@NotNull final Statistics statistics) {
        final List<StatisticsListElement> elements = converter.convert(statistics);
        adapter.replaceAll(elements);
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

    private void setupList(@NotNull final Context context) {
        adapter = new StatisticsListAdapter(context);
        converter = new StatisticsListElementConverter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
    }
}