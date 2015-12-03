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
import android.widget.TextView;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Gallerys;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.net.JsonService;
import de.itsawade.itsawade.ui.adapter.ImagesAdapter;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Images>>{

    private static Gallerys gallerys;
    private RecyclerView recyclerView;

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
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Images>> loader, List<Images> data) {
        if (data != null) {
            recyclerView.setAdapter(new ImagesAdapter(data));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Images>> loader) {

    }

    public void onLoad() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://it-sawade.de")
                        //.baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonService jsonService = retrofit.create(JsonService.class);

        jsonService.getImages(gallerys.getId()).enqueue(new Callback<List<Images>>() {
            @Override
            public void onResponse(Response<List<Images>> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    List a = response.body().subList(0,response.body().size());
                    recyclerView.setAdapter(new ImagesAdapter(response.body()));
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
