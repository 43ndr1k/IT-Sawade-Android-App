package de.itsawade.itsawade.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.async.GenericLoader;
import de.itsawade.itsawade.logic.PlaceholderLogic;
import de.itsawade.itsawade.model.BlogPost;
import de.itsawade.itsawade.model.BlogPostList;
import de.itsawade.itsawade.model.Gallerys;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.ui.adapter.BlogPostsAdapter;
import de.itsawade.itsawade.util.Function;
import de.itsawade.itsawade.util.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostListFragment extends Fragment implements LoaderManager.LoaderCallbacks<BlogPostList> {

    private static final int BLOG_POST_LIST_DOWNLOADER = 0;
    RecyclerView recyclerView;
    List<BlogPost> blogPostList;

    public BlogPostListFragment() {
        // Required empty public constructor
    }

    public static BlogPostListFragment newInstance() {
        BlogPostListFragment blogPostListFragment = new BlogPostListFragment();

        return blogPostListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View blogPostListFragmentView = inflater.inflate(R.layout.fragment_blog_post_list,container,false);

        final FragmentActivity c = getActivity();
        recyclerView = (RecyclerView) blogPostListFragmentView.findViewById(R.id.recylerViewBlogPostList);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));

        c.getSupportLoaderManager().restartLoader(BLOG_POST_LIST_DOWNLOADER, null, this);
        return blogPostListFragmentView;
    }


    @Override
    public Loader<BlogPostList> onCreateLoader(int id, Bundle args) {
        final FragmentActivity ctx = getActivity();
        return new GenericLoader<>(ctx, new Function<BlogPostList>() {
            @Override
            public BlogPostList apply() {
                return PlaceholderLogic.getInstance().getBlogPostList();
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<BlogPostList> loader, BlogPostList data) {
         BlogPostsAdapter adapter = new BlogPostsAdapter(data, new OnItemClickListener<String>() {


             @Override
             public void onItemClick(Gallerys item, int position) {

             }

             @Override
             public void onItemClick(int position) {

             }

             @Override
             public void onItemClick(Images item, int position) {

             }
         });


        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<BlogPostList> loader) {

    }


}
