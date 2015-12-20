package de.itsawade.itsawade.ui.activitys;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
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

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.async.GenericLoader;
import de.itsawade.itsawade.logic.PlaceholderLogic;
import de.itsawade.itsawade.model.NewBlogPost;
import de.itsawade.itsawade.util.Function;

public class AddNewBlogPostActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private static final int RESULT_IMAGE = 1;
    private static final String THUMBNAILS_URL = "thumbnail_url";
    private static final String NEW_POST = "new_post";
    private static String IMAGE_URL = "image_url";
    String url;
    ImageButton imageButton;
    EditText editTextNewBlogPost, editNewBlogPostTitleText;
    private String thumbnail;

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
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.action_back:
                this.onBackPressed();
                return true;
            case R.id.action_upload:
                post();
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void post() {

        Bundle args = new Bundle();

        NewBlogPost post = new NewBlogPost(1,editNewBlogPostTitleText.getText().toString(), editTextNewBlogPost.getText().toString(),url);

        args.putParcelable(NEW_POST,post);

        getSupportLoaderManager().restartLoader(0,args,this);
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


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        if(args.containsKey(NEW_POST)) {
            final NewBlogPost newBlogPost = args.getParcelable(NEW_POST);
            return new GenericLoader<>(this, new Function<String>() {
                @Override
                public String apply() {
                    return PlaceholderLogic.getInstance().createNewBlogPost(newBlogPost);
                }
            });
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null) {
            Toast.makeText(this, "Posted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
