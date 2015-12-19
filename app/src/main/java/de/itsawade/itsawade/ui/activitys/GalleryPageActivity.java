package de.itsawade.itsawade.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.async.GenericLoader;
import de.itsawade.itsawade.logic.PlaceholderLogic;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.ui.adapter.ImageAdapter;
import de.itsawade.itsawade.util.Function;

public class GalleryPageActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Images>> {


    private static final int PHOTO_DOWNLOADER = 0;
    private static final String IMAGE_URL_THUMBNAIL = "thumbnail_url";

    List<Images> images;
    Images image;

    Gallery gallery;
    private static String IMAGE_URL = "image_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_page);

        // Note that Gallery view is deprecated in Android 4.1---
        gallery = (Gallery) findViewById(R.id.gallery1);
        getSupportLoaderManager().restartLoader(PHOTO_DOWNLOADER, null, this);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                image = images.get(position);
                // display the images selected
                ImageView imageView = (ImageView) findViewById(R.id.image1);
                Picasso.with(getApplicationContext())
                        .load(images.get(position).getThumbnailUrl())
                        .into(imageView);
            }
        });

    }

    @Override
    public Loader<List<Images>> onCreateLoader(int id, Bundle args) {
        return new GenericLoader<>(this, new Function<List<Images>>() {
            @Override
            public List<Images> apply() {
                return PlaceholderLogic.getInstance().getAllImages();
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<List<Images>> loader, List<Images> data) {
        images = data;
        gallery.setAdapter(new ImageAdapter(this, images));
    }

    @Override
    public void onLoaderReset(Loader<List<Images>> loader) {

    }
    Intent intent = new Intent();
    public void buttonClick(View view) {

        intent.putExtra(IMAGE_URL, image.getBaseUrl());
        intent.putExtra(IMAGE_URL_THUMBNAIL, image.getThumbnailUrl());
        setResult(RESULT_OK, intent);
        onBackPressed();



    }
}

