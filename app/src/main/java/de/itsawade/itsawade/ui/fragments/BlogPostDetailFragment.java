package de.itsawade.itsawade.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.itsawade.itsawade.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostDetailFragment extends Fragment {


    public BlogPostDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_post_detail, container, false);
    }

}
