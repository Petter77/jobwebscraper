package com.petter77.jobwebscraper.parser;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.jsoup.nodes.Element;

@Component
public class JustJoinItParser extends AbstractJobSiteParser { 

    public JustJoinItParser(
            @Value("${scraper.url.justjoinit}") String url,
            @Value("${scraper.css-selector.justjoinit.offer}") String offer,
            @Value("${scraper.css-selector.justjoinit.title}") String title,
            @Value("${scraper.css-selector.justjoinit.technologies}") String technologies,
            @Value("${scraper.css-selector.justjoinit.url}") String urlSelector) {
        super(url, offer, title, technologies, urlSelector);
    }    


    public String getSiteName() {
        return "justjoin.it";
    }

    @Override
    protected String parseUrl(Element offer) {
        String url = "https://justjoin.it";
        return  url.concat(offer.select(getUrlSelector()).attr("href"));
    }
}
