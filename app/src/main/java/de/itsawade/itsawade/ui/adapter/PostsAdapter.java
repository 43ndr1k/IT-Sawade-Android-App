package de.itsawade.itsawade.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.BlogPost;
import de.itsawade.itsawade.util.OnItemClickListener;


/**
 * Created by Steve on 13.11.2015.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<BlogPost> postList;
    private OnItemClickListener<String> onItemClickListener;

    public PostsAdapter(List<BlogPost> blogPostList, OnItemClickListener<String> onItemClickListener) {
        this.postList = blogPostList;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallerys_json,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BlogPost blogPost = postList.get(position);

        holder.id.setText(String.valueOf(blogPost.getId()));
        holder.content.setText(blogPost.getContent());


        holder.title.setText(blogPost.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(blogPost, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView content;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            id = (TextView)itemView.findViewById(R.id.galleryId);
            content = (TextView)itemView.findViewById(R.id.content);
            title = (TextView)itemView.findViewById(R.id.galleryTitle);

        }
    }
}
