package de.itsawade.itsawade.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.Gallerys;
import de.itsawade.itsawade.util.OnItemClickListener;



public class GallerysAdapter extends RecyclerView.Adapter<GallerysAdapter.ViewHolder> {

    private List<Gallerys> gallerysList;
    private OnItemClickListener<String> onItemClickListener;

    public GallerysAdapter(List<Gallerys> gallerysList, OnItemClickListener<String> onItemClickListener) {
        this.gallerysList = gallerysList;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallerys_json,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Gallerys gallerys = gallerysList.get(position);

        holder.id.setText(String.valueOf(gallerys.getId()));
        holder.content.setText(gallerys.getContent());
        final Gallerys item = gallerysList.get(position);

        holder.title.setText(gallerys.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(item, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return gallerysList.size();
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
