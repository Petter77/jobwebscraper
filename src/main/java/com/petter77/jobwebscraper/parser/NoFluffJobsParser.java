package com.petter77.jobwebscraper.parser;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.jsoup.nodes.Element;

@Component
public class NoFluffJobsParser extends AbstractJobSiteParser { 

    public NoFluffJobsParser(
            @Value("${scraper.url.nofluffjobs}") String url,
            @Value("${scraper.css-selector.nofluffjobs.offer}") String offer,
            @Value("${scraper.css-selector.nofluffjobs.title}") String title,
            @Value("${scraper.css-selector.nofluffjobs.technologies}") String technologies,
            @Value("${scraper.css-selector.nofluffjobs.url}") String urlSelector) {
        super(url, offer, title, technologies, urlSelector);
    }    

    public String getSiteName() {
        return "NoFluffJobs.com";
    }

    @Override
    protected String parseUrl(Element offer) {
        String url = "https://nofluffjobs.com";
        return  url.concat(offer.select(getUrlSelector()).attr("href"));
    }
}
