package de.itsawade.itsawade.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Comment;
import de.itsawade.itsawade.util.DateConvert;
import de.itsawade.itsawade.util.OnItemClickListener;

/**
 * Created by hendrik on 15.12.15.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    List<Comment> list;
    private OnItemClickListener<String> onItemClickListener;

    public CommentAdapter (List<Comment> list) {
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String commentContent = list.get(position).getComment();
        String userName = list.get(position).getUser_name() + " ";
        String date = list.get(position).getSubmit_date();

        DateConvert datee = new DateConvert();
        String a = datee.DateConvert(date);



        holder.userName.setText(userName);
        holder.date.setText(a);
        holder.content.setText(commentContent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView userName;
        private TextView date;
        private TextView content;

        public ViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.blogPostComentUserName);
            date = (TextView) itemView.findViewById(R.id.blogPostComentSubmit_date);
            content = (TextView) itemView.findViewById(R.id.blogPostCommentContent);



        }
    }

}
