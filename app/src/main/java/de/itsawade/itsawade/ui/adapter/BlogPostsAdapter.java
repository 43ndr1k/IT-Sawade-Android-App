package de.itsawade.itsawade.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.BlogPost;
import de.itsawade.itsawade.model.BlogPostList;
import de.itsawade.itsawade.util.OnItemClickListener;


/**
 * Created by Steve on 13.11.2015.
 */
public class BlogPostsAdapter extends RecyclerView.Adapter<BlogPostsAdapter.ViewHolder> {

    private BlogPostList postList;
    private OnItemClickListener<String> onItemClickListener;

    public BlogPostsAdapter(BlogPostList blogPostList, OnItemClickListener<String> onItemClickListener) {
        this.postList = blogPostList;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_post,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BlogPost blogPost = postList.getResults().get(position);


        holder.content.setText(blogPost.getContentText());
        holder.autor.setText("posted by: " + blogPost.getUser().getFirst_name());
        holder.progressbar.setVisibility(View.VISIBLE);

        Picasso.with(holder.itemView.getContext())
                .load(blogPost.getFeatured_image())
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




        holder.title.setText(blogPost.getTitle());

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
        return postList.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View progressbar;
        private ImageView imageView;

        private TextView content;
        private TextView title;
        private TextView autor;

        public ViewHolder(View itemView) {
            super(itemView);

            progressbar = itemView.findViewById(R.id.progressbarBlogPost);
            imageView = (ImageView)itemView.findViewById(R.id.blogFeatured_image);
            content = (TextView)itemView.findViewById(R.id.blogPostContent);
            title = (TextView)itemView.findViewById(R.id.blogPostTitle);
            autor = (TextView)itemView.findViewById(R.id.blogPostAutor);

        }
    }
}
