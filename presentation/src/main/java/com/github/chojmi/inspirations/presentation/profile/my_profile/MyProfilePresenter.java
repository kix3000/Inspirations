package com.github.chojmi.inspirations.presentation.profile.my_profile;

import com.github.chojmi.inspirations.domain.common.UseCase;
import com.github.chojmi.inspirations.domain.entity.people.PersonEntity;
import com.github.scribejava.core.model.OAuth1AccessToken;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

import static com.github.chojmi.inspirations.domain.utils.Preconditions.checkNotNull;

public class MyProfilePresenter implements MyProfileContract.Presenter {
    private final UseCase<String, OAuth1AccessToken> getToken;
    private final UseCase<Void, PersonEntity> getLoginData;
    private CompositeDisposable disposables;
    private MyProfileContract.View view;

    public MyProfilePresenter(@NonNull UseCase<Void, PersonEntity> getLoginData,
                              @NonNull UseCase<String, OAuth1AccessToken> getToken) {
        this.getLoginData = checkNotNull(getLoginData);
        this.getToken = checkNotNull(getToken);
    }

    @Override
    public void setView(@NonNull MyProfileContract.View view) {
        this.disposables = new CompositeDisposable();
        this.view = checkNotNull(view);
        fetchToken();
    }

    @Override
    public void destroyView() {
        this.disposables.clear();
        this.view = null;
    }

    @Override
    public void loginSuccessful() {
        fetchToken();
    }

    private void fetchProfile() {
        disposables.add(getLoginData.process(null)
                .doOnComplete(() -> view.toggleProgressBar(false))
                .subscribe(uiModel -> {
            view.toggleProgressBar(uiModel.isInProgress());
            if (uiModel.isInProgress()) {
                return;
            }
            if (uiModel.isSucceed()) {
                view.showProfile(uiModel.getResult());
            }
                }, Timber::e));
    }

    private void fetchToken() {
        disposables.add(getToken.process(null)
                .doOnComplete(() -> view.toggleProgressBar(false))
                .subscribe(uiModel -> {
            view.toggleProgressBar(uiModel.isInProgress());
            if (uiModel.isInProgress()) {
                return;
            }
            if (uiModel.isSucceed()) {
                fetchProfile();
            }
        }, Timber::e));
    }
}
