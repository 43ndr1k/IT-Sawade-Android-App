package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hendrik on 13.12.15.
 */
public class Category implements Parcelable {

    int id;
    String title, slug;

    public Category() {

    }

    public Category(int id, String title, String slug) {
        this.id = id;
        this.title = title;
        this.slug = slug;
    }

    protected Category(Parcel in) {
        id = in.readInt();
        title = in.readString();
        slug = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(slug);
    }
}
