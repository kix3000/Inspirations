package com.github.chojmi.inspirations.data.source.local;

import com.github.chojmi.inspirations.domain.entity.people.PersonEntity;
import com.github.chojmi.inspirations.domain.repository.AuthTestDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LocalAuthTestDataSource implements AuthTestDataSource {
    @Inject
    public LocalAuthTestDataSource() {
    }

    @Override
    public Observable<PersonEntity> getLoginData() {
        return Observable.empty();
    }
}
