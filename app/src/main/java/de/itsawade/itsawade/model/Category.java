package de.itsawade.itsawade.model;

/**
 * Created by hendrik on 13.12.15.
 */
public class Category {

    int id;
    String title, slug;

    public Category() {

    }

    public Category(int id, String title, String slug) {
        this.id = id;
        this.title = title;
        this.slug = slug;
    }

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
}
