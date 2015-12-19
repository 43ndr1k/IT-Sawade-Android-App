package de.itsawade.itsawade.ui.activitys;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import de.itsawade.itsawade.R;

public class AddNewBlogPostActivity extends AppCompatActivity {

    private static final int RESULT_IMAGE = 1;
    private static final String THUMBNAILS_URL = "thumbnail_url";
    private static String IMAGE_URL = "image_url";
    String url;
    ImageButton imageButton;
    private String thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_blog_post);

        ActionBar ab = getActionBar();

        imageButton = (ImageButton) findViewById(R.id.imageButton);

      //  url = intent.getExtras().getString(IMAGE_URL);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_blog_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.action_back:
                this.onBackPressed();
                return true;
            case R.id.action_upload:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void imageWaehlen(View view) {
        Intent intent = new Intent();
        intent.setClass(this, GalleryPageActivity.class);
        startActivityForResult(intent, RESULT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                url = data.getStringExtra(IMAGE_URL);
                thumbnail = data.getStringExtra(THUMBNAILS_URL);
            }

            if (thumbnail != null) {
                Picasso.with(getApplicationContext())
                        .load(thumbnail)
                        .into(imageButton);
            }

        }
    }
}
