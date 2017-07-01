package com.github.chojmi.inspirations.data.source.remote.interceptors;

import com.github.chojmi.inspirations.data.source.remote.service.OAuthService;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.github.chojmi.inspirations.domain.utils.Preconditions.checkNotNull;

public class SigningInterceptor implements Interceptor {
    private final OAuthService oAuthService;

    public SigningInterceptor(@NonNull OAuthService oAuthService) {
        this.oAuthService = checkNotNull(oAuthService);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (oAuthService.containsAccessToken()) {
            String signedUrl = oAuthService.signRequest(request.url().toString());
            return chain.proceed(request.newBuilder().url(signedUrl).build());
        }
        return chain.proceed(request);
    }
}
