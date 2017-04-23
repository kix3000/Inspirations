package com.github.chojmi.inspirations.data.entity.people;

import android.os.Parcelable;

import com.github.chojmi.inspirations.data.entity.helpers.ContentHolder;
import com.github.chojmi.inspirations.domain.entity.people.PersonEntity;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class PersonEntityImpl implements Parcelable, PersonEntity {
    public static TypeAdapter<PersonEntityImpl> typeAdapter(Gson gson) {
        return new AutoValue_PersonEntityImpl.GsonTypeAdapter(gson);
    }

    @SerializedName("username")
    abstract ContentHolder getUsernameContentHolder();

    @SerializedName("description")
    abstract ContentHolder getDescriptionContentHolder();

    @Override
    public String getUsername() {
        return getUsernameContentHolder().getContent();
    }

    @Override
    public String getDescription() {
        return getDescriptionContentHolder().getContent();
    }
}
