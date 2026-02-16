package com.petter77.jobwebscraper.parser;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.jsoup.nodes.Element;

@Component
public class NoFluffJobsParser extends AbstractJobSiteParser { 
    @Value("${scraper.url.nofluffjobs}")
    private String scraperUrl;

    @Value("${scraper.css-selector.nofluffjobs.offer}")
    private String offerSelector;

    @Value("${scraper.css-selector.nofluffjobs.title}")
    private String titleSelector;

    @Value("${scraper.css-selector.nofluffjobs.technologies}")
    private String technologiesSelector;

    @Value("${scraper.css-selector.nofluffjobs.url}")
    private String urlSelector;

    protected String getUrl() { return scraperUrl; }
    protected String getOfferSelector() { return offerSelector; }
    protected String getTitleSelector() { return titleSelector; }
    protected String getTechnologiesSelector() { return technologiesSelector; }
    protected String getUrlSelector() { return urlSelector; }

    public String getSiteName() {
        return "NoFluffJobs.com";
    }

    @Override
    protected String parseUrl(Element offer) {
        String url = "nofluffjobs.com";
        return  url.concat(offer.select(urlSelector).attr("href"));
    }
}
