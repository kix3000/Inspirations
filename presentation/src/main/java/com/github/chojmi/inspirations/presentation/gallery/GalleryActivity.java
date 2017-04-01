package com.github.chojmi.inspirations.presentation.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.chojmi.inspirations.presentation.R;
import com.github.chojmi.inspirations.presentation.blueprints.BaseActivity;

public class GalleryActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, GalleryActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GalleryFragment fragment = (GalleryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        DaggerGalleryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(fragment);
    }
}
