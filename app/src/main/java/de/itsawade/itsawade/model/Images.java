package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hendrik on 28.11.15.
 */
public class Images implements Parcelable {

    private String description;
    private int id;
    private String file;

    public Images(String file) {
        this.file = file;
    }

    public Images(Parcel source) {
        this.file = source.readString();
        this.description = source.readString();
        this.id = source.readInt();

    }

    public Images() {}

    public String getThumbnailUrl() {
        String u = file.substring(file.lastIndexOf("/"));
        String a = file.substring(0,(file.length() - u.length()));
        String[] f = u.split("\\.");
        String url = "http://it-sawade.de/static/media/" + a + "/.thumbnails" + u + f[0] + "-600x450.jpg";
        return  url;
    }

    public String getUrl() {
        return  "http://it-sawade.de/static/media/" + file;
    }

    public String getBaseUrl() {
        return file;
    }


    public void setThumbnailUrl(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(file);
        dest.writeString(description);
        dest.writeInt(id);
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {


            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
