package com.petter77.jobwebscraper.parser;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.jsoup.nodes.Element;

@Component
public class JustJoinItParser extends AbstractJobSiteParser { 
    @Value("${scraper.url.justjoinit}")
    private String scraperUrl;

    @Value("${scraper.css-selector.justjoinit.offer}")
    private String offerSelector;

    @Value("${scraper.css-selector.justjoinit.title}")
    private String titleSelector;

    @Value("${scraper.css-selector.justjoinit.technologies}")
    private String technologiesSelector;

    @Value("${scraper.css-selector.justjoinit.url}")
    private String urlSelector;

    protected String getUrl() { return scraperUrl; }
    protected String getOfferSelector() { return offerSelector; }
    protected String getTitleSelector() { return titleSelector; }
    protected String getTechnologiesSelector() { return technologiesSelector; }
    protected String getUrlSelector() { return urlSelector; }

    public String getSiteName() {
        return "justjoin.in";
    }

    @Override
    protected String parseUrl(Element offer) {
        String url = "justjoin.it";
        return  url.concat(offer.select(urlSelector).attr("href"));
    }
}
