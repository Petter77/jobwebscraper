package com.petter77.jobwebscraper.parser;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class PracujPlParser extends AbstractJobSiteParser{

    public PracujPlParser(
            @Value("${scraper.url.pracujpl}") String url,
            @Value("${scraper.css-selector.pracujpl.offer}") String offer,
            @Value("${scraper.css-selector.pracujpl.title}") String title,
            @Value("${scraper.css-selector.pracujpl.technologies}") String technologies,
            @Value("${scraper.css-selector.pracujpl.url}") String urlSelector) {
        super(url, offer, title, technologies, urlSelector);
    }    

    public String getSiteName() {
        return "Pracuj.pl";
    }

}
