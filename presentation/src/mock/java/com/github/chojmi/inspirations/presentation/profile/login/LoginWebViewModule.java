package com.github.chojmi.inspirations.presentation.profile.login;

import com.github.chojmi.inspirations.presentation.profile.ProfileScope;

import dagger.Module;
import dagger.Provides;

@ProfileScope
@Module
public class LoginWebViewModule {
    @Provides
    LoginWebViewContract.Presenter providePhotoViewPresenter() {
        return new LoginWebViewPresenter();
    }
}