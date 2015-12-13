package de.itsawade.itsawade.util;


import de.itsawade.itsawade.model.Gallerys;
import de.itsawade.itsawade.model.Images;

/**
 * Created by Steve on 04.11.2015.
 */
public interface OnItemClickListener<T> {
    void onItemClick(Gallerys item, int position);
    void onItemClick(int position);
    void onItemClick(Images item, int position);
}
