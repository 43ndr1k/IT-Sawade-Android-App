package de.itsawade.itsawade.model;

/**
 * Created by hendrik on 28.11.15.
 */
public class Images {

    private String description;
    private int id;
    private String file;



    public String getThumbnailUrl() {
        return "http://it-sawade.de/static/media/" + file;
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
}
