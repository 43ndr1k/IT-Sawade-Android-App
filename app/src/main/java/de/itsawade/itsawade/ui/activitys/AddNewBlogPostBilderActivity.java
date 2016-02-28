package de.itsawade.itsawade.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.ui.adapter.AddNewBlogPostBilderListAdapter;
import de.itsawade.itsawade.util.OnClickListener;

public class AddNewBlogPostBilderActivity extends AppCompatActivity {

    private static final String IMAGE = "image";
    private static final int RESULT_IMAGE = 3;
    private static final String LIST = "BildList";
    private List<Images> list;

    AddNewBlogPostBilderListAdapter adapter;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_blog_post_bilder);

        recyclerView = (RecyclerView) findViewById(R.id.listviewBilder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = getIntent().getParcelableArrayListExtra("BildList");

        adapter = new AddNewBlogPostBilderListAdapter(list, new OnClickListener() {
            @Override
            public void onButtonClick(int position) {
                list.remove(position);
                recyclerView.removeViewAt(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position,list.size());
            }
        });
        recyclerView.setAdapter(adapter);

    }

    public void addBild(View view) {
        Intent intent = new Intent();
        intent.setClass(this, GalleryPageActivity.class);
        startActivityForResult(intent, RESULT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                Images image = data.getParcelableExtra(IMAGE);
                if (image != null) {
                    list.add(image);
                }
                adapter.notifyDataSetChanged();

            }
        }
    }

    public void addList(View view) {
        Intent intent = new Intent();
        intent.putExtra(LIST, (ArrayList<Images>) list);
        setResult(RESULT_OK, intent);
        onBackPressed();
    }
}
