package de.itsawade.itsawade.util;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hendrik on 05.12.15.
 */
public interface OnTouchListener<T> {
    void setOnTouch(View v, MotionEvent event);
}
