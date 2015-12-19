package de.itsawade.itsawade.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Images;


/**
 * Created by hendrik on 18.12.15.
 */
public class ImageAdapter extends BaseAdapter{


        private Context context;
        private int itemBackground;
        private List<Images> images;

        public ImageAdapter(Context c, List<Images> images)
        {   this.images = images;
            context = c;
            // sets a grey background; wraps around the images
            TypedArray a =c.obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }
        // returns the number of images
        public int getCount() {
            return images.size();
        }
        // returns the ID of an item
        public Images getItem(int position) {
            return images.get(position);
        }
        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }
        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            Picasso.with(context)
                    .load(images.get(position).getThumbnailUrl())
                    .into(imageView);

            imageView.setLayoutParams(new Gallery.LayoutParams(500,500));

            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }

}
