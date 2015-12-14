package de.itsawade.itsawade.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Images;
import de.itsawade.itsawade.util.OnItemClickListener;

/**
 * Created by hendrik on 14.12.15.
 */
public class BlogPostDetailAdapter extends RecyclerView.Adapter<BlogPostDetailAdapter.ViewHolder> {

    private final OnItemClickListener<String> onItemClickListener;
    List<Images> blogPostImageList;

    public BlogPostDetailAdapter(List<Images> blogPostImageList, OnItemClickListener<String> onItemClickListener) {
        this.blogPostImageList = blogPostImageList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public BlogPostDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_item_detail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String imageUrl = blogPostImageList.get(position).getBaseUrl();
        Picasso.with(holder.itemView.getContext())
                .load(imageUrl)
                .resize(1350, 0)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return blogPostImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View progressbar;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            progressbar = itemView.findViewById(R.id.progressbarBlogPostDetail);
            imageView = (ImageView) itemView.findViewById(R.id.blog_imageDetail);


        }
    }

}
