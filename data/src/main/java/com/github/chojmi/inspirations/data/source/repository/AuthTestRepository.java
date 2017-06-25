package com.github.chojmi.inspirations.data.source.repository;

import com.github.chojmi.inspirations.data.source.Local;
import com.github.chojmi.inspirations.data.source.Remote;
import com.github.chojmi.inspirations.domain.entity.people.UserEntity;
import com.github.chojmi.inspirations.domain.repository.AuthTestDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import static dagger.internal.Preconditions.checkNotNull;

public class AuthTestRepository implements AuthTestDataSource {

    private final AuthTestDataSource localAuthTestDataSource;

    private final AuthTestDataSource remoteAuthTestDataSource;

    @Inject
    AuthTestRepository(@Remote @NonNull AuthTestDataSource localAuthTestDataSource,
                       @Local @NonNull AuthTestDataSource remoteAuthTestDataSource) {
        this.localAuthTestDataSource = checkNotNull(localAuthTestDataSource);
        this.remoteAuthTestDataSource = checkNotNull(remoteAuthTestDataSource);
    }

    @Override
    public Observable<UserEntity> getLoginData() {
        return Observable.concat(localAuthTestDataSource.getLoginData(),
                remoteAuthTestDataSource.getLoginData()).firstElement().toObservable();
    }
}