package com.petter77.jobwebscraper.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String technologies;

    @Column(nullable = false, unique = true)
    private String url;

    public Offer(){}

    public Offer(String title, String technologies, String url) {
        this.title = title;
        this.technologies = technologies;
        this.url = url;
    }

}

