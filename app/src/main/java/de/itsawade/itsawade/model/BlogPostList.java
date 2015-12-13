package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hendrik on 13.12.15.
 */
public class BlogPostList implements Parcelable {

    List<BlogPost> results = new ArrayList<>();

     public BlogPostList() {

     }

    public BlogPostList(List<BlogPost> results) {
        this.results = results;
    }

    protected BlogPostList(Parcel in) {
        results = in.createTypedArrayList(BlogPost.CREATOR);
    }

    public static final Creator<BlogPostList> CREATOR = new Creator<BlogPostList>() {
        @Override
        public BlogPostList createFromParcel(Parcel in) {
            return new BlogPostList(in);
        }

        @Override
        public BlogPostList[] newArray(int size) {
            return new BlogPostList[size];
        }
    };

    public List<BlogPost> getResults() {
        return results;
    }

    public void setResults(List<BlogPost> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }
}
