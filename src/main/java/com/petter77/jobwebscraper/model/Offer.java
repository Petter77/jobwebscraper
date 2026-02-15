package com.petter77.jobwebscraper.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String published;

    @Column(nullable = false)
    private String technologies;

    @Column(nullable = false, unique = true)
    private String url;

    public Offer(){};

    public Offer(String title, String technologies, String published, String url) {
        this.title = title;
        this.technologies = technologies;
        this.published = published;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getTechnologies() {
        return technologies;
    }

    public String getPublished() {
        return published;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

