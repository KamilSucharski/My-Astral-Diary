package com.sengami.domain_splash.view;

import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;

public interface SplashView extends WithErrorHandler, WithLoadingIndicator {

    void navigateToMainView();
}