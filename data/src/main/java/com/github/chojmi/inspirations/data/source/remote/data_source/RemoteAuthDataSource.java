package com.github.chojmi.inspirations.data.source.remote.data_source;

import com.github.chojmi.inspirations.data.entity.auth.FrobEntityImpl;
import com.github.chojmi.inspirations.data.source.remote.service.AuthService;
import com.github.chojmi.inspirations.data.source.remote.service.RemoteQueryProducer;
import com.github.chojmi.inspirations.domain.entity.auth.FrobEntity;
import com.github.chojmi.inspirations.domain.entity.auth.TokenEntity;
import com.github.chojmi.inspirations.domain.repository.AuthDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static dagger.internal.Preconditions.checkNotNull;

public class RemoteAuthDataSource implements AuthDataSource {
    private AuthService authService;
    private RemoteQueryProducer remoteQueryProducer;

    @Inject
    public RemoteAuthDataSource(@NonNull AuthService authService, @NonNull RemoteQueryProducer remoteQueryProducer) {
        this.authService = checkNotNull(authService);
        this.remoteQueryProducer = checkNotNull(remoteQueryProducer);
    }

    @Override
    public Observable<FrobEntity> getFrob() {
        return authService.getFrob(remoteQueryProducer.produceGetFrob())
                .subscribeOn(Schedulers.newThread())
                .map(r -> FrobEntityImpl.create(r.getFrob(), remoteQueryProducer.produceLoginPageUrl(r.getFrob())))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TokenEntity> getToken(@NonNull String frob) {
        return authService.getToken(remoteQueryProducer.produceGetToken(checkNotNull(frob)))
                .subscribeOn(Schedulers.newThread())
                .map(r -> (TokenEntity) r)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onNewTokenEntity(TokenEntity tokenEntity) {

    }
}
