package com.petter77.jobwebscraper;
import java.util.List;
import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OfferParser {

    public List<Offer> parseAll(Elements offers) {
        List<Offer> result = new ArrayList<>();
        for (Element offer : offers) {
            result.add(parseSingle(offer));
        }
        for (Offer offer : result) {
            System.out.println(offer.getTitle());
            System.out.println(offer.getTechnologies());
            System.out.println(offer.getPublished());
            System.out.println(offer.getUrl());
            System.out.println();
        } 
        return result;
    }

    public Offer parseSingle(Element offer) {
        Offer result = new Offer();
        result.setTitle(parseTitle(offer));
        result.setTechnologies(parseTechnologies(offer));
        result.setPublished(parsePublished(offer));
        result.setUrl(parseUrl(offer));
        return result;
    }

    public String parseTitle(Element offer) {
        String result = offer.select("h2[data-test=offer-title]").text();
        return result;
    }

    public String parseTechnologies(Element offer) {
        Elements technologiesList = offer.select("span[data-test=technologies-item]");
        List<String> technologies = new ArrayList<>();
        for (Element technologyTemp : technologiesList) {
            String technologyReady = technologyTemp.text();
            technologies.add(technologyReady);
        }
        String result = String.join(", ", technologies);
        return result;
    }

    public String parsePublished(Element offer) {
        String published = offer.select("p[data-test=text-added]").text();
        String result = published.split(": ")[2];
        return result;
    }

    public String parseUrl(Element offer) {
        String result = offer.select("a[data-test=link-offer-title]").attr("href");
        return result;
    }
}
