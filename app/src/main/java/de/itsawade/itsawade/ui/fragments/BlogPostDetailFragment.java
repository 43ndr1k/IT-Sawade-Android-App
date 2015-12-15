package de.itsawade.itsawade.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.BlogPost;
import de.itsawade.itsawade.model.Gallerys;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.ui.activitys.ImageDetailActivity;
import de.itsawade.itsawade.ui.adapter.BlogPostDetailAdapter;
import de.itsawade.itsawade.ui.adapter.CommentAdapter;
import de.itsawade.itsawade.util.DateConvert;
import de.itsawade.itsawade.util.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostDetailFragment extends Fragment {



    public static final String BlogPost_ITEM = "BlogPost_item";


    RecyclerView recyclerView, recyclerViewComments;
    BlogPost blogPost;
    int pos;
    TextView blogPostTitleDetail;
    TextView blogPostContentDetail;
    TextView blogPostAutorDetail;
    TextView blogPostDateDetail;
    TextView blogPostCommentDetail;

    public BlogPostDetailFragment() {
        // Required empty public constructor
    }

    public static BlogPostDetailFragment newInstance(BlogPost blogPost) {
        BlogPostDetailFragment blogPostDetailFragment = new BlogPostDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable(BlogPost_ITEM, blogPost);
        blogPostDetailFragment.setArguments(args);


        return blogPostDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewBlogPostDetail = inflater.inflate(R.layout.fragment_blog_post_detail, container, false);

        Bundle args = getArguments();
        if (args.containsKey(BlogPost_ITEM)) {
            blogPost = args.getParcelable(BlogPost_ITEM);

        }

        blogPostAutorDetail = (TextView) viewBlogPostDetail.findViewById(R.id.blogPostAutorDetail);
        blogPostTitleDetail = (TextView) viewBlogPostDetail.findViewById(R.id.blogPostTitleDetail);
        blogPostContentDetail = (TextView) viewBlogPostDetail.findViewById(R.id.blogPostContentDetail);
        blogPostDateDetail = (TextView) viewBlogPostDetail.findViewById(R.id.blogPostDateDetail);
        blogPostCommentDetail = (TextView) viewBlogPostDetail.findViewById(R.id.blogPostComentCountDetail);

        blogPostAutorDetail.setText("Posted by: " + blogPost.getUser().getFirst_name());
        blogPostTitleDetail.setText(blogPost.getTitle());
        blogPostContentDetail.setText(blogPost.getContentText());


        DateConvert date = new DateConvert();
        String a = date.DateConvert(blogPost.getPublish_date());

        blogPostDateDetail.setText(" | At: " + a);
        blogPostCommentDetail.setText(" | " + blogPost.getComments_count() + " comment");

        //blogPostContentDetail.setMovementMethod(new ScrollingMovementMethod());

        final FragmentActivity c = getActivity();

        /**
         * RecylerView bilder
         */
        recyclerView = (RecyclerView) viewBlogPostDetail.findViewById(R.id.recylerViewBlogPostDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        BlogPostDetailAdapter adapter = new BlogPostDetailAdapter(blogPost.getContentImagelist(), new OnItemClickListener<String>() {
            @Override
            public void onItemClick(Gallerys item, int position) {

            }

            @Override
            public void onItemClick(int position) {
                final FragmentActivity c = getActivity();
                Intent intent = new Intent();
                intent.setClass(c, ImageDetailActivity.class);

                intent.putExtra(ImageDetailActivity.IMAGE_ITEM,blogPost.getContentImagelist().get(position));
                intent.putExtra(ImageDetailActivity.URL_ITEM,blogPost.getContentImagelist().get(position).getBaseUrl());
                startActivity(intent);
            }

            @Override
            public void onItemClick(Images item, int position) {

            }
        });
        recyclerView.setAdapter(adapter);

        if (blogPost.getContentImagelist().size() < 1) {
            recyclerView.setVisibility(viewBlogPostDetail.GONE);
        }

        /**
         * RecylerView Comments
         */
        recyclerViewComments = (RecyclerView) viewBlogPostDetail.findViewById(R.id.recylerViewBlogPostDetailComments);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(c));
        CommentAdapter commentAdapter = new CommentAdapter(blogPost.getComments());
        recyclerViewComments.setAdapter(commentAdapter);

        if (blogPost.getComments().size() < 1) {
            recyclerViewComments.setVisibility(viewBlogPostDetail.GONE);
        }


        return viewBlogPostDetail;
    }


}
