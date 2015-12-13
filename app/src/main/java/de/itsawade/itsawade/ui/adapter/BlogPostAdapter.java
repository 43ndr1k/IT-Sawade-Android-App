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
import de.itsawade.itsawade.util.OnItemClickListener;


/**
 * Created by Steve on 13.11.2015.
 */
public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.ViewHolder> {


    private List<String> imageList;
    private OnItemClickListener<String> onItemClickListener;

    public BlogPostAdapter(List<String> imageList, OnItemClickListener<String> onItemClickListener) {
        this.imageList = imageList;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_item_detail,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String url = imageList.get(position);

        holder.progressbar.setVisibility(View.VISIBLE);

        Picasso.with(holder.itemView.getContext())
                .load(url)
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
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View progressbar;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            progressbar = itemView.findViewById(R.id.progressbarBlogPostDetail);
            imageView = (ImageView)itemView.findViewById(R.id.blog_imageDetail);


        }
    }
}
