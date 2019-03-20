package com.sengami.domain_splash.view;

import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;

public interface SplashView extends WithErrorHandler, WithLoadingIndicator {

    void navigateToMainView();
}