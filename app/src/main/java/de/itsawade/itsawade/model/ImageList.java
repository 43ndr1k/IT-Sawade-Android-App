package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hendrik on 05.12.15.
 */
public class ImageList implements Parcelable {

    private List<Images> list;

    public ImageList(Parcel source) {
        if (list == null) {
            this.list = new ArrayList();
        }
        source.readTypedList(list, Images.CREATOR);
    }

    public ImageList(List<Images> list) {
        this.list = list;
    }

    public static final Creator<ImageList> CREATOR = new Creator<ImageList>() {
        @Override
        public ImageList createFromParcel(Parcel in) {
            return new ImageList(in);
        }

        @Override
        public ImageList[] newArray(int size) {
            return new ImageList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(list);
    }

    public List<Images> getList() {
        return list;
    }

    public void setList(List<Images> list) {
        this.list = list;
    }
}
