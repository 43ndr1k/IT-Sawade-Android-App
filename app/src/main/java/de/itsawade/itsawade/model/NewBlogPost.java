package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hendrik on 20.12.15.
 */
public class NewBlogPost implements Parcelable {

    int user;
    String title, content, slug, featured_image;
    boolean in_sitemap = true, gen_description = true;

    public NewBlogPost(int user, String title, String content, String featured_image) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.featured_image = featured_image;
    }

    public NewBlogPost() {

    }


    protected NewBlogPost(Parcel in) {
        user = in.readInt();
        title = in.readString();
        content = in.readString();
        slug = in.readString();
        featured_image = in.readString();
        in_sitemap = in.readByte() != 0;
        gen_description = in.readByte() != 0;
    }

    public static final Creator<NewBlogPost> CREATOR = new Creator<NewBlogPost>() {
        @Override
        public NewBlogPost createFromParcel(Parcel in) {
            return new NewBlogPost(in);
        }

        @Override
        public NewBlogPost[] newArray(int size) {
            return new NewBlogPost[size];
        }
    };

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public boolean isIn_sitemap() {
        return in_sitemap;
    }

    public void setIn_sitemap(boolean in_sitemap) {
        this.in_sitemap = in_sitemap;
    }

    public boolean isGen_description() {
        return gen_description;
    }

    public void setGen_description(boolean gen_description) {
        this.gen_description = gen_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(slug);
        dest.writeString(featured_image);
        dest.writeByte((byte) (in_sitemap ? 1 : 0));
        dest.writeByte((byte) (gen_description ? 1 : 0));
    }
}
