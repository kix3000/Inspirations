package com.github.chojmi.inspirations.domain.repository;

import com.github.scribejava.core.model.OAuth1AccessToken;

import io.reactivex.Observable;

public interface AuthDataSource {
    Observable<String> getAuthorizationUrl();

    Observable<OAuth1AccessToken> getAccessToken(String oauthVerifier);
}