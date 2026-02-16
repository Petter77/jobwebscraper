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
    protected abstract String getUrl();
    protected abstract String getOfferSelector();
    protected abstract String getTitleSelector();
    protected abstract String getTechnologiesSelector();
    protected abstract String getUrlSelector();

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
