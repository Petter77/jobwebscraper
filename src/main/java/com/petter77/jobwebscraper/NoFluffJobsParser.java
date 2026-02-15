package com.petter77.jobwebscraper;
import java.util.List;
import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

@Component
public class NoFluffJobsParser implements JobSiteParser{
    @Value("${scraper.url}")
    private String scraperUrl;

    @Value("${scraper.css-selector.offer}")
    private String offerSelector;

    @Value("${scraper.css-selector.title}")
    private String titleSelector;

    @Value("${scraper.css-selector.technologies}")
    private String technologiesSelector;

    @Value("${scraper.css-selector.published}")
    private String publishedSelector;

    @Value("${scraper.css-selector.url}")
    private String urlSelector;

    public String getSiteName() {
        return "NoFluffJobs.com";
    }

    public List<Offer> parse() throws IOException{
        Document doc = Jsoup.connect(scraperUrl).get();
        Elements offers = doc.select(offerSelector);
        List<Offer> result = new ArrayList<>();
        for (Element offer : offers) {
            result.add(parseSingle(offer));
        }

        return result;
    }

    private Offer parseSingle(Element offer) {
        Offer result = new Offer();
        result.setTitle(parseTitle(offer));
        result.setTechnologies(parseTechnologies(offer));
        result.setPublished(parsePublished(offer));
        result.setUrl(parseUrl(offer));
        return result;
    }

    private String parseTitle(Element offer) {
        return offer.select(titleSelector).text();
    }

    private String parseTechnologies(Element offer) {
        Elements technologiesList = offer.select(technologiesSelector);
        List<String> technologies = new ArrayList<>();
        for (Element technologyTemp : technologiesList) {
            String technologyReady = technologyTemp.text();
            technologies.add(technologyReady);
        }

        return String.join(", ", technologies);
    }

    private String parsePublished(Element offer) {
        String published = offer.select(publishedSelector).text();
        return published.split(": ")[2];
    }

    private String parseUrl(Element offer) {
        return offer.select(urlSelector).attr("href");
    }
}
