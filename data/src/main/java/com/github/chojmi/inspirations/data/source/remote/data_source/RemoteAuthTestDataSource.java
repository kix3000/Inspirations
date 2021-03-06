package com.github.chojmi.inspirations.data.source.remote.data_source;

import com.github.chojmi.inspirations.data.entity.auth.LoginDataEntityImpl;
import com.github.chojmi.inspirations.data.source.remote.service.AuthTestService;
import com.github.chojmi.inspirations.data.source.remote.service.RemoteQueryProducer;
import com.github.chojmi.inspirations.domain.entity.people.PersonEntity;
import com.github.chojmi.inspirations.domain.repository.AuthTestDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import static com.github.chojmi.inspirations.domain.utils.Preconditions.checkNotNull;

public class RemoteAuthTestDataSource implements AuthTestDataSource {
    private final AuthTestService testService;
    private final RemoteQueryProducer remoteQueryProducer;

    @Inject
    public RemoteAuthTestDataSource(@NonNull AuthTestService testService, @NonNull RemoteQueryProducer remoteQueryProducer) {
        this.testService = checkNotNull(testService);
        this.remoteQueryProducer = checkNotNull(remoteQueryProducer);
    }

    @Override
    public Observable<PersonEntity> getLoginData() {
        return testService.loadLoginData(remoteQueryProducer.produceLoadLoginData())
                .map(LoginDataEntityImpl::getUser);
    }
}
