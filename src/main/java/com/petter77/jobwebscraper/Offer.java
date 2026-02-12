package com.petter77.jobwebscraper;

public class Offer {
    String title;
    String published;
    String technologies;
    String url;

    public String getTitle(){return title;}
    public String getTechnologies(){return technologies;}
    public String getPublished(){return published;}
    public String getUrl(){return url;}

    public void setTitle(String title) {this.title = title;}
    public void setTechnologies(String technologies) {this.technologies = technologies;}
    public void setPublished(String published) {this.published = published;}
    public void setUrl(String url) {this.url = url;}
}

