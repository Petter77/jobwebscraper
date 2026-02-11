package com.petter77.jobwebscraper;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

@Component
public class JobScraper implements CommandLineRunner {

    public class Offer {
        String title;
        String published;
        String technologies;
        String url;

        Offer (String title, String technologies, String published, String url) {
            this.title = title;
            this.technologies = technologies;
            this.published = published;
            this.url = url;
        }
    }


    public void run(String... args) throws Exception {

        Document doc = Jsoup.connect("https://it.pracuj.pl/praca?et=17%2C1&sc=0&itth=38%2C54%2C41%2C37%2C36%2C34").get();

        Elements offers = doc.select(".tiles_b18pwp01.core_po9665q"); 

        System.out.println("Offers found: " + offers.size());

        List<Offer> offersToSave = new ArrayList<>();

        for (Element offer : offers) {

            String title = offer.select("h2[data-test=offer-title]").text();

            Elements technologiesList = offer.select("span[data-test=technologies-item]");
            List<String> technologies = new ArrayList<>();
            for (Element technologyTemp : technologiesList) {
                String technologyReady = technologyTemp.text();
                technologies.add(technologyReady);
            }
            String technologiesString = String.join(", ", technologies);

            String published = offer.select("p[data-test=text-added]").text();
            published = published.split(": ")[2];

            String url = offer.select("a[data-test=link-offer-title]").attr("href");
            Offer newOffer = new Offer(title, technologiesString, published, url); 

            offersToSave.add(newOffer);
        }

        for (Offer offer : offersToSave) {
            System.out.println(offer.title);
            System.out.println(offer.technologies);
            System.out.println(offer.published);
            System.out.println(offer.url);
            System.out.println();
        }

    }
}
