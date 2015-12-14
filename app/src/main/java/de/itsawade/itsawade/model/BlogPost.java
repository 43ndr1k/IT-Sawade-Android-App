package de.itsawade.itsawade.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by hendrik on 01.12.15.
 */
public class BlogPost implements Parcelable{

    //BlogPost
    int count, id, comments_count;
    String next, previous;

    String publish_date, updated, title, url, short_url, content, slug, allow_comments,
    tags, featured_image;

    //user
    String username, email,first_name, last_name;
    int id_user; // muss id heißen


    User user = new User(username, id_user, email,first_name, last_name);

    List<Category> categories = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();


    List<Images> contentImagelist = new ArrayList<>();
    private String contentText;
    private String snipselText;


    public BlogPost(Parcel source) {
        this.count = source.readInt();
        this.id = source.readInt();
        this.next = source.readString();
        this.previous = source.readString();
        this.publish_date = source.readString();
        this.updated = source.readString();
        this.title = source.readString();
        this.url = source.readString();
        this.short_url = source.readString();
        this.content = source.readString();
        this.slug = source.readString();
        this.allow_comments = source.readString();
        this.comments_count = source.readInt();
        this.tags = source.readString();
        this.featured_image = source.readString();
        this.user = new User(source.readString(), source.readInt(), source.readString(),
                source.readString(), source.readString());
        source.readTypedList(categories, Category.CREATOR);
        source.readTypedList(comments,Comment.CREATOR);
        source.readTypedList(contentImagelist,Images.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeString(next);
        dest.writeString(previous);
        dest.writeString(publish_date);
        dest.writeString(updated);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(short_url);
        dest.writeString(content);
        dest.writeString(slug);
        dest.writeString(allow_comments);
        dest.writeInt(comments_count);
        dest.writeString(tags);
        dest.writeString(featured_image);
        dest.writeInt(id);
        dest.writeString(user.getUsername());
        dest.writeInt(user.getId());
        dest.writeString(user.getEmail());
        dest.writeString(user.getFirst_name());
        dest.writeString(user.getLast_name());
        dest.writeList(categories);
        dest.writeList(comments);
        dest.writeList(contentImagelist);
    }

    public static final Parcelable.Creator<BlogPost> CREATOR = new Parcelable.Creator<BlogPost>() {
        @Override
        public BlogPost createFromParcel(Parcel source) {
            return new BlogPost(source);
        }

        @Override
        public BlogPost[] newArray(int size) {
            return new BlogPost[size];
        }
    };

    public BlogPost() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        setContentText(getContent());
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> category) {
        this.categories = category;
    }

    public String getAllow_comments() {
        return allow_comments;
    }

    public void setAllow_comments(String allow_comments) {
        this.allow_comments = allow_comments;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFeatured_image() {
        return "http://it-sawade.de/static/media/" + featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContentText(String text) {

        Document document= Jsoup.parse(text);
        String baseUri = "http://it-sawade.de";
        Elements links = document.select("img");
        for (Element link : links)  {
            if (!link.attr("src").toLowerCase().startsWith("http://"))    {
                link.attr("src", baseUri + link.attr("src"));
            }
            Images image = new Images(link.attr("src"));
            contentImagelist.add(image);
        }
        String doc = Jsoup.parse(document.html()).text();
        this.contentText = doc;
        setContentTextSnipsel(doc);
    }

    public String getContentText() {
        setContentText(getContent());
        return contentText;
    }

    public List<Images> getContentImagelist() {
        return contentImagelist;
    }

    private void setContentTextSnipsel(String text) {

        Pattern pattern = Pattern.compile("(?<=[.?!;])\\s+(?=\\p{Lu})");
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            snipselText = m.group(0);
        }
    }
    public String getContentTextSnipsel() {
        return snipselText;
    }


}
