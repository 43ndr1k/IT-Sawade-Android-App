package de.itsawade.itsawade.ui.fragments;


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
import de.itsawade.itsawade.ui.adapter.CommentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends Fragment {


    private static String BlogPost_ITEM = "blogPost";
    private RecyclerView recyclerViewComments;
    private BlogPost blogPost;
    private TextView blogPostCommentTitle;

    public CommentsFragment() {
        // Required empty public constructor
    }

    public static CommentsFragment newInstance(BlogPost blogPost) {
        CommentsFragment commentfragment = new CommentsFragment();

        Bundle args = new Bundle();
        args.putParcelable(BlogPost_ITEM, blogPost);
        commentfragment.setArguments(args);


        return commentfragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        FragmentActivity c = getActivity();

        Bundle args = getArguments();
        if (args.containsKey(BlogPost_ITEM)) {
            blogPost = args.getParcelable(BlogPost_ITEM);

        }
        blogPostCommentTitle = (TextView) view.findViewById(R.id.blogPostCommentTitle);
        blogPostCommentTitle.setText(getResources().getString(R.string.CommentsTitle));

        /**
         * RecylerView Comments
         */
        recyclerViewComments = (RecyclerView) view.findViewById(R.id.recylerViewBlogPostDetailComments);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(c));
        CommentAdapter commentAdapter = new CommentAdapter(blogPost.getComments());
        recyclerViewComments.setAdapter(commentAdapter);

        
        
        return view;
    }

}
