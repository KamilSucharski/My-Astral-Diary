package com.sengami.gui_statistics.view;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sengami.android_operation.di.module.OperationConfigurationModule;
import com.sengami.android_operation.implementation.AndroidOperationConfiguration;
import com.sengami.android_operation.implementation.error.ToastErrorHandler;
import com.sengami.android_operation.implementation.loading.ViewVisibilityLoadingIndicator;
import com.sengami.domain_base.model.Statistics;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_statistics.view.StatisticsView;
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

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public final class StatisticsFragment
    extends BaseFragment<Presenter<StatisticsView>, FragmentStatisticsBinding>
    implements StatisticsView {

    @NotNull
    private final Subject<Boolean> refreshStatisticsTrigger = PublishSubject.create();
    private BaseAdapter<StatisticsListElement, StatisticsListElementType> adapter;
    private ElementConverter<Statistics, StatisticsListElement> converter;

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<StatisticsView> presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void inject(@NotNull final Context context) {
        final OperationConfiguration operationConfiguration = AndroidOperationConfiguration
            .create()
            .withErrorHandler(new ToastErrorHandler(context))
            .withLoadingIndicator(new ViewVisibilityLoadingIndicator(() -> binding.loadingWheelOverlay));

        DaggerStatisticsComponent
            .builder()
            .operationConfigurationModule(new OperationConfigurationModule(operationConfiguration))
            .build()
            .inject(this);
    }

    @Override
    protected void init(@NotNull final Context context) {
        super.init(context);
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

    private void setupList(@NotNull final Context context) {
        adapter = new StatisticsListAdapter();
        converter = new StatisticsListElementConverter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
    }
}