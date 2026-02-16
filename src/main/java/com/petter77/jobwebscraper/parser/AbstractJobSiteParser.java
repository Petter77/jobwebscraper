package com.petter77.jobwebscraper.parser;

import com.petter77.jobwebscraper.model.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public abstract class AbstractJobSiteParser implements JobSiteParser{
    private final String scraperUrl;
    private final String offerSelector;
    private final String titleSelector;
    private final String technologiesSelector;
    private final String urlSelector;

    protected AbstractJobSiteParser(String scraperUrl, String offerSelector,
            String titleSelector, String technologiesSelector, String urlSelector) {
        this.scraperUrl = scraperUrl;
        this.offerSelector = offerSelector;
        this.titleSelector = titleSelector;
        this.technologiesSelector = technologiesSelector;
        this.urlSelector = urlSelector;
    }
    protected String getUrl() { return scraperUrl; }
    protected String getOfferSelector() { return offerSelector; }
    protected String getTitleSelector() { return titleSelector; }
    protected String getTechnologiesSelector() { return technologiesSelector; }
    protected String getUrlSelector() { return urlSelector; }

    @Override
    public List<Offer> parse() throws IOException{
        Document doc = Jsoup.connect(getUrl()).get();
        Elements offers = doc.select(getOfferSelector());
        List<Offer> result = new ArrayList<>();
        for (Element offer : offers) {
            result.add(parseSingle(offer));
        }

        return result;
    }

    protected Offer parseSingle(Element offer) {
        Offer result = new Offer();
        result.setTitle(parseTitle(offer));
        result.setTechnologies(parseTechnologies(offer));
        result.setUrl(parseUrl(offer));
        return result;
    }

    protected String parseTitle(Element offer) {
        return offer.select(getTitleSelector()).text();
    }

    protected String parseTechnologies(Element offer) {
        Elements technologiesList = offer.select(getTechnologiesSelector());
        List<String> technologies = new ArrayList<>();
        for (Element technologyTemp : technologiesList) {
            String technologyReady = technologyTemp.text();
            technologies.add(technologyReady);
        }

        return String.join(", ", technologies);
    }

    protected String parseUrl(Element offer) {
        return offer.select(getUrlSelector()).attr("href");
    }
}
