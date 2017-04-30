package com.github.chojmi.inspirations.presentation.model.gallery;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Photo implements Parcelable {
    public static Photo create(String title, String url, Person person) {
        return new AutoValue_Photo(title, url, person);
    }

    public abstract String getTitle();

    public abstract String getUrl();

    public abstract Person getPerson();
}