package de.itsawade.itsawade.ui.activitys;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.async.GenericLoader;
import de.itsawade.itsawade.logic.PlaceholderLogic;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.model.NewBlogPost;
import de.itsawade.itsawade.util.Function;

public class AddNewBlogPostActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Integer>{

    private static final int RESULT_IMAGE = 1;
    private static final String THUMBNAILS_URL = "thumbnail_url";
    private static final String NEW_POST = "new_post";
    private static final int CAMERA_REQUEST = 2;
    private static final int RESULT_IMAGELIST = 3;

    private static final String LIST = "BildList";
    private static String IMAGE_URL = "image_url";
    String url;

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    ImageButton imageButton;
    EditText editTextNewBlogPost, editNewBlogPostTitleText;
    private String thumbnail;
    private List<Images> list = new ArrayList<Images>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_blog_post);

        ActionBar ab = getActionBar();

        imageButton = (ImageButton) findViewById(R.id.imageButton);

        editNewBlogPostTitleText = (EditText) findViewById(R.id.editNewBlogPostTitleText);
        editTextNewBlogPost = (EditText) findViewById(R.id.editTextNewBlogPost);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_blog_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back:
                this.onBackPressed();
                return true;
            case R.id.action_upload:
                post();
                onBackPressed();
                return true;
            case R.id.action_uploadFile:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);

                return true;
            case R.id.action_newPicture:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void post() {

        Bundle args = new Bundle();

        String content = "<p>" + editTextNewBlogPost.getText().toString() + "</p>";

        for (int i = 0; i < list.size();i++) {
            String bild = "<p><img src=\"" + "/static/media/" + list.get(i).getBaseUrl() + "\"" + " width=\"300\" /></p>";
            content += bild;
        }

        NewBlogPost post = new NewBlogPost(1,editNewBlogPostTitleText.getText().toString(), content,url);

        args.putParcelable(NEW_POST, post);

        getSupportLoaderManager().restartLoader(0, args, this);
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

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
        }

        if (requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPath(selectedImageUri);
        }

        if (requestCode == RESULT_IMAGELIST) {
            if (resultCode == RESULT_OK) {
                list = data.getParcelableArrayListExtra("BildList");
            }
        }
    }


    @Override
    public Loader<Integer> onCreateLoader(int id, Bundle args) {
        if(args.containsKey(NEW_POST)) {
            final NewBlogPost newBlogPost = args.getParcelable(NEW_POST);
            return new GenericLoader<>(this, new Function<Integer>() {
                @Override
                public Integer apply() {
                    return PlaceholderLogic.getInstance().createNewBlogPost(newBlogPost);
                }
            });
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Integer> loader, Integer data) {
        if (data != null && data.equals(200)) {
            Toast.makeText(this, "Posted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Post failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Integer> loader) {

    }



    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    public void onNewImageOnClick(View view) {
    }

    public void bilder(View view) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(LIST, (ArrayList<Images>) list);
        intent.setClass(this, AddNewBlogPostBilderActivity.class);
        startActivityForResult(intent, RESULT_IMAGELIST);
    }
}
