package de.itsawade.itsawade.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.util.OnClickListener;

/**
 * Created by hendrik on 14.12.15.
 */
public class AddNewBlogPostBilderListAdapter extends RecyclerView.Adapter<AddNewBlogPostBilderListAdapter.ViewHolder> {

    private final OnClickListener onClickListener;
    List<Images> list;

    public AddNewBlogPostBilderListAdapter(List<Images> list, OnClickListener onClickListener) {
        this.list = list;
        this.onClickListener = onClickListener;

    }

    @Override
    public AddNewBlogPostBilderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_bild_blog_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String imageUrl = list.get(position).getThumbnailUrl();
        Picasso.with(holder.itemView.getContext())
                .load(imageUrl)
                .resize(300, 0)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                  if (onClickListener != null) {
                    onClickListener.onButtonClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View progressbar;
        private ImageView imageView;
        private ImageButton buttonDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            progressbar = itemView.findViewById(R.id.progressbarBildListe);
            imageView = (ImageView) itemView.findViewById(R.id.imageBlogPostList);
            buttonDelete = (ImageButton) itemView.findViewById(R.id.buttonDeleteBild);


        }
    }

}
