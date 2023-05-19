package com.mako.api.news;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    private String Id;
    @Column(name = "title")
    private String title;
    @Column(name = "category")

    private String category;

    @Column(name = "image")

    private String image;

    @Column(name = "description")

    private String description;
    @Column(name = "url")

    private String url;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
