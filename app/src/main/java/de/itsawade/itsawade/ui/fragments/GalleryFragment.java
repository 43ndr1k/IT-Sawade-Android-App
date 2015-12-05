package de.itsawade.itsawade.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.async.ImagesLoader;
import de.itsawade.itsawade.model.BlogPost;
import de.itsawade.itsawade.model.Gallerys;
import de.itsawade.itsawade.model.ImageList;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.ui.adapter.ImagesAdapter;
import de.itsawade.itsawade.util.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Images>>{

    private static Gallerys gallerys;
    private RecyclerView recyclerView;
    private List<Images> imagesList;
    ImageList list;
    private static final int PHOTO_DOWNLOADER = 0;

    public GalleryFragment() {
        // Required empty public constructor
    }

    public static final String GALLERY_ITEM = "gallery_item";

    public static GalleryFragment newInstance(Gallerys gallerys) {
        GalleryFragment galleryFragment = new GalleryFragment();

        Bundle args = new Bundle();
        args.putParcelable(GALLERY_ITEM, gallerys);
        galleryFragment.setArguments(args);

        return galleryFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGalleryFragment = inflater.inflate(R.layout.fragment_gallery, container, false);

        Bundle args = getArguments();
        if (args.containsKey(GALLERY_ITEM)) {
            gallerys = args.getParcelable(GALLERY_ITEM);

        }
        final FragmentActivity c = getActivity();
        recyclerView = (RecyclerView)viewGalleryFragment.findViewById(R.id.recylcerViewImages);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        TextView textView = (TextView)viewGalleryFragment.findViewById(R.id.titleGallery);
        textView.setText(gallerys.getTitle());

        onLoad();

        return viewGalleryFragment;
    }


    @Override
    public Loader<List<Images>> onCreateLoader(int id, Bundle args) {
        final FragmentActivity ctx = getActivity();
        return new ImagesLoader(ctx, gallerys.getId());
    }

    @Override
    public void onLoadFinished(Loader<List<Images>> loader, List<Images> data) {
        if (data != null) {
            imagesList = data;
            list = new ImageList(imagesList);
            ImagesAdapter imagesAdapter = new ImagesAdapter(data, new OnItemClickListener<String>() {
                @Override
                public void onItemClick(Gallerys item, int position) {

                }

                @Override
                public void onItemClick(BlogPost item, int position) {

                }

                @Override
                public void onItemClick(Images item, int position) {
                        load(item, position);
                }
            });
            recyclerView.setAdapter(imagesAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Images>> loader) {

    }

    public void onLoad() {

        final FragmentActivity c = getActivity();
        c.getSupportLoaderManager().restartLoader(PHOTO_DOWNLOADER,null,this);

    }

    private void load(Images images, int pos) {
        final FragmentActivity c = getActivity();
        FragmentTransaction transaction = c.getSupportFragmentManager().beginTransaction();
        ImageDetailFragment imageDetailFragment = ImageDetailFragment.newInstance(list,pos);
        transaction.replace(R.id.activityContainer,imageDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
