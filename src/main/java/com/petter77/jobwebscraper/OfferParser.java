package com.petter77.jobwebscraper;
import java.util.List;
import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class OfferParser {

    public List<Offer> parseAll(Elements offers) {
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
        return offer.select("h2[data-test=offer-title]").text();
    }

    private String parseTechnologies(Element offer) {
        Elements technologiesList = offer.select("span[data-test=technologies-item]");
        List<String> technologies = new ArrayList<>();
        for (Element technologyTemp : technologiesList) {
            String technologyReady = technologyTemp.text();
            technologies.add(technologyReady);
        }

        return String.join(", ", technologies);
    }

    private String parsePublished(Element offer) {
        String published = offer.select("p[data-test=text-added]").text();
        return published.split(": ")[2];
    }

    private String parseUrl(Element offer) {
        return offer.select("a[data-test=link-offer-title]").attr("href");
    }
}
