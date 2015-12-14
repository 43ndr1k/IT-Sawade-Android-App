package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hendrik on 01.12.15.
 */
public class User implements Parcelable {

    int id;
    String username, email,first_name, last_name;

    public User(String last_name, int id, String username, String email, String first_name) {
        this.last_name = last_name;
        this.id = id;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
    }

    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        email = in.readString();
        first_name = in.readString();
        last_name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(first_name);
        dest.writeString(last_name);
    }
}
