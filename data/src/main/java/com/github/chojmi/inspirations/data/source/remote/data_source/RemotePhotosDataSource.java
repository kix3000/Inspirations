package com.github.chojmi.inspirations.data.source.remote.data_source;

import com.github.chojmi.inspirations.data.source.remote.service.PhotosService;
import com.github.chojmi.inspirations.data.source.remote.service.RemoteQueryProducer;
import com.github.chojmi.inspirations.domain.entity.GalleryEntity;
import com.github.chojmi.inspirations.domain.entity.photos.PhotoCommentsEntity;
import com.github.chojmi.inspirations.domain.entity.photos.PhotoFavsEntity;
import com.github.chojmi.inspirations.domain.entity.photos.PhotoInfoEntity;
import com.github.chojmi.inspirations.domain.entity.photos.PhotoSizeListEntity;
import com.github.chojmi.inspirations.domain.repository.PhotosDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import static com.github.chojmi.inspirations.domain.utils.Preconditions.checkNotNull;

public class RemotePhotosDataSource implements PhotosDataSource {

    private PhotosService photosService;
    private RemoteQueryProducer remoteQueryProducer;

    @Inject
    RemotePhotosDataSource(@NonNull PhotosService photosService, @NonNull RemoteQueryProducer remoteQueryProducer) {
        this.photosService = checkNotNull(photosService);
        this.remoteQueryProducer = checkNotNull(remoteQueryProducer);
    }

    @Override
    public Observable<PhotoInfoEntity> loadPhotoInfo(String photoId) {
        return photosService.loadPhotoInfo(remoteQueryProducer.produceLoadPhotoInfo(photoId))
                .map(photoInfoEntity -> photoInfoEntity);
    }

    @Override
    public Observable<PhotoFavsEntity> loadPhotoFavs(String photoId, int page) {
        return photosService.loadPhotoFavs(remoteQueryProducer.produceLoadPhotoFavsQuery(photoId, page + 1))
                .map(photoFavsEntity -> photoFavsEntity);
    }

    @Override
    public Observable<PhotoCommentsEntity> loadPhotoComments(String photoId) {
        return photosService.loadPhotoComments(remoteQueryProducer.produceLoadPhotoComments(photoId))
                .map(photoCommentsEntity -> photoCommentsEntity);
    }

    @Override
    public Observable<PhotoSizeListEntity> loadPhotoSizes(String photoId) {
        return photosService.loadPhotoSizes(remoteQueryProducer.produceLoadPhotoSizes(photoId))
                .map(photoSizeList -> photoSizeList);
    }

    @Override
    public Observable<GalleryEntity> loadSearchPhoto(String text) {
        return photosService.loadSearchPhoto(remoteQueryProducer.produceLoadSearchPhoto(text))
                .map(gallery -> gallery);
    }

    @Override
    public Observable<Void> addComment(String photoId, String commentText) {
        return photosService.addComment(remoteQueryProducer.produceAddComment(photoId, commentText));
    }
}