package com.github.chojmi.inspirations.presentation.di;

import com.github.chojmi.inspirations.presentation.gallery.ui.comments.CommentsActivity;
import com.github.chojmi.inspirations.presentation.gallery.ui.comments.CommentsModule;
import com.github.chojmi.inspirations.presentation.gallery.ui.favs.FavsActivity;
import com.github.chojmi.inspirations.presentation.gallery.ui.favs.FavsModule;
import com.github.chojmi.inspirations.presentation.main.MainActivity;
import com.github.chojmi.inspirations.presentation.main.MainActivityFragmentProvider;
import com.github.chojmi.inspirations.presentation.photo.PhotoViewActivity;
import com.github.chojmi.inspirations.presentation.photo.PhotoViewModule;
import com.github.chojmi.inspirations.presentation.profile.login.LoginWebViewActivity;
import com.github.chojmi.inspirations.presentation.profile.login.LoginWebViewModule;
import com.github.chojmi.inspirations.presentation.profile.user_profile.UserProfileActivity;
import com.github.chojmi.inspirations.presentation.profile.user_profile.UserProfileModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = MainActivityFragmentProvider.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = PhotoViewModule.class)
    abstract PhotoViewActivity bindPhotoViewActivity();

    @ContributesAndroidInjector(modules = LoginWebViewModule.class)
    abstract LoginWebViewActivity bindLoginWebViewActivity();

    @ContributesAndroidInjector(modules = UserProfileModule.class)
    abstract UserProfileActivity bindUserProfileActivity();

    @ContributesAndroidInjector(modules = FavsModule.class)
    abstract FavsActivity bindFavsActivity();

    @ContributesAndroidInjector(modules = CommentsModule.class)
    abstract CommentsActivity bindCommentsActivity();
}
