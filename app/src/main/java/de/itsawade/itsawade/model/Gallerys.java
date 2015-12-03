package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hendrik on 28.11.15.
 */
public class Gallerys implements Parcelable{

    private int id;
    private String content;
    private String title;

    public Gallerys(Parcel source) {
        this.title = source.readString();
        this.content = source.readString();
        this.id = source.readInt();
    }

    public Gallerys() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(id);
    }

    public static final Creator<Gallerys> CREATOR = new Creator<Gallerys>() {
        @Override
        public Gallerys createFromParcel(Parcel source) {


            return new Gallerys(source);
        }

        @Override
        public Gallerys[] newArray(int size) {
            return new Gallerys[size];
        }
    };
}
