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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.async.StringLoader;
import de.itsawade.itsawade.model.Gallerys;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.ui.adapter.GallerysAdapter;
import de.itsawade.itsawade.util.GallerysDeserializer;
import de.itsawade.itsawade.util.OnItemClickListener;


public class GallerysFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{

    private  static final int STRING_LOADER = 0;
    private static final String URL_KEY = "url";
    private static final String URL_GALLERYS = "https://it-sawade.de/api/gallery";
    private RecyclerView recyclerView;
    static final int PICK_CONTACT_REQUEST = 1;
    public static final String DATA = "data";

    public GallerysFragment() {
        // Required empty public constructor
    }

    public static GallerysFragment newInstance() {
        GallerysFragment gallerysFragment = new GallerysFragment();

        return gallerysFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGallerysFragment = inflater.inflate(R.layout.fragment_gallerys, container, false);
        final FragmentActivity c = getActivity();
        recyclerView = (RecyclerView) viewGallerysFragment.findViewById(R.id.recylerViewMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));


        Bundle args = new Bundle();
        args.putString(URL_KEY,URL_GALLERYS);
        c.getSupportLoaderManager().restartLoader(STRING_LOADER, args, this);

        return viewGallerysFragment;

    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        if (args.containsKey(URL_KEY)) {
            final FragmentActivity c = getActivity();
            return new StringLoader(c,args.getString(URL_KEY));
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        if (!data.equals("")) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Gallerys.class,new GallerysDeserializer()).create();

            Type type = new TypeToken<Collection<Gallerys>>(){}.getType();
            List<Gallerys> gallerysList = gson.fromJson(data, type);

            GallerysAdapter gallerysAdapter = new GallerysAdapter(gallerysList, new OnItemClickListener<String>() {
                @Override
                public void onItemClick(Gallerys item, int position) {
                    //Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    onClickItem(item);

                }

                @Override
                public void onItemClick(int position) {

                }

                @Override
                public void onItemClick(Images item, int position) {

                }
            });
            recyclerView.setAdapter(gallerysAdapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    public void onClickItem(Gallerys item) {
        final FragmentActivity c = getActivity();
        FragmentTransaction transaction = c.getSupportFragmentManager().beginTransaction();
        GalleryFragment galleryFragment = GalleryFragment.newInstance(item);
        transaction.replace(R.id.activityContainer,galleryFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
