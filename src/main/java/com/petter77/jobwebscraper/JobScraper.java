package com.petter77.jobwebscraper;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Component
public class JobScraper implements CommandLineRunner {

    private OfferParser offerParser = new OfferParser();

    public void run(String... args) throws Exception {

        Document doc = Jsoup.connect("https://it.pracuj.pl/praca?et=17%2C1&sc=0&itth=38%2C54%2C41%2C37%2C36%2C34").get();

        Elements offers = doc.select(".tiles_b18pwp01.core_po9665q"); 

        List<Offer> result = offerParser.parseAll(offers); 

    }
}
