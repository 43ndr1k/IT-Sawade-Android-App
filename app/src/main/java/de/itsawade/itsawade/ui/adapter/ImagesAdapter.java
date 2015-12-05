package de.itsawade.itsawade.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.util.OnItemClickListener;


/**
 * Created by hendrik on 28.11.15.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private final List<Images> images;
    private OnItemClickListener<String> onItemClickListener;

    public ImagesAdapter(List<Images> images, OnItemClickListener<String> onItemClickListener) {
        this.images = images;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       final Images image = images.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(image.getThumbnailUrl())
                .resize(1350,1000)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(image, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }

}
