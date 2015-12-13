package de.itsawade.itsawade.model;

/**
 * Created by hendrik on 13.12.15.
 */
public class Comment {

    int id;
    String user, user_name, comment, submit_date, is_public, is_removed;

    public Comment() {

    }

    public Comment(int id, String user, String user_name, String comment, String submit_date, String is_public, String is_removed) {
        this.id = id;
        this.user = user;
        this.user_name = user_name;
        this.comment = comment;
        this.submit_date = submit_date;
        this.is_public = is_public;
        this.is_removed = is_removed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
}
