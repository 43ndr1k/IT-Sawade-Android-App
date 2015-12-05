package de.itsawade.itsawade.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.model.ImageList;
import de.itsawade.itsawade.util.OnTouchListener;

/**
 * Created by hendrik on 04.12.15.
 */
public class CustomSwipeAdapter extends PagerAdapter {

    private final ImageList list;



    private Context ctx;
    private LayoutInflater layoutInflater;
    private int pos;


    private OnTouchListener<String> onTouchListener;

    public CustomSwipeAdapter(Context ctx, ImageList list1, int pos, OnTouchListener<String> onTouchListener) {
        this.list = list1;
        this.ctx = ctx;
        this.onTouchListener = onTouchListener;
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.imageswipe);
        TextView textView = (TextView) item_view.findViewById(R.id.imageDescription);


        Picasso.with(ctx)
                .load(list.getList().get(pos).getThumbnailUrl())
                .resize(2000, 0) //(4000,0)
                .into(imageView);
        textView.setText(list.getList().get(pos).getDescription());

        item_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (onTouchListener != null) {
                    onTouchListener.setOnTouch(v, event);
                }
                    return true;
                }
            });

            container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }



}
