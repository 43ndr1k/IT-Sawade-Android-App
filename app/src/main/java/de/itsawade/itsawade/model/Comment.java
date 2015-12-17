package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hendrik on 13.12.15.
 */
public class Comment implements Parcelable {

    int id;
    String user_name, comment, submit_date, is_public, is_removed;

    private String username;
    private int id_user;
    private String email;
    private String first_name;
    private String last_name;

    User user = new User();

    public Comment() {

    }

    public Comment(int id, User user, String user_name, String comment, String submit_date, String is_public, String is_removed) {
        this.id = id;
        this.user = user;
        this.user_name = user_name;
        this.comment = comment;
        this.submit_date = submit_date;
        this.is_public = is_public;
        this.is_removed = is_removed;
    }

    protected Comment(Parcel in) {
        id = in.readInt();
        user_name = in.readString();
        comment = in.readString();
        submit_date = in.readString();
        is_public = in.readString();
        is_removed = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public String getIs_public() {
        return is_public;
    }

    public void setIs_public(String is_public) {
        this.is_public = is_public;
    }

    public String getIs_removed() {
        return is_removed;
    }

    public void setIs_removed(String is_removed) {
        this.is_removed = is_removed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(user_name);
        dest.writeString(comment);
        dest.writeString(submit_date);
        dest.writeString(is_public);
        dest.writeString(is_removed);
    }
}
