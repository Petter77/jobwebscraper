package com.petter77.jobwebscraper.parser;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class PracujPlParser extends AbstractJobSiteParser{
    @Value("${scraper.url.pracujpl}")
    private String scraperUrl;

    @Value("${scraper.css-selector.pracujpl.offer}")
    private String offerSelector;

    @Value("${scraper.css-selector.pracujpl.title}")
    private String titleSelector;

    @Value("${scraper.css-selector.pracujpl.technologies}")
    private String technologiesSelector;

    @Value("${scraper.css-selector.pracujpl.url}")
    private String urlSelector;

    protected String getUrl() { return scraperUrl; }
    protected String getOfferSelector() { return offerSelector; }
    protected String getTitleSelector() { return titleSelector; }
    protected String getTechnologiesSelector() { return technologiesSelector; }
    protected String getUrlSelector() { return urlSelector; }

    public String getSiteName() {
        return "Pracuj.pl";
    }

}
