package com.petter77.jobwebscraper;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ScrapingScheduler {
    private final OfferParser offerParser;
    private final OfferRepository offerRepository;

    @Value("${scraper.url}")
    private String scraperUrl;

    @Value("${scraper.css-selector.offer}")
    private String scraperCssSelectorOffer;

    public ScrapingScheduler(OfferParser offerParser, OfferRepository offerRepository) {
        this.offerParser = offerParser;
        this.offerRepository = offerRepository;
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void scrape() {
        try{
            Document doc = Jsoup.connect(scraperUrl).get();

            Elements offers = doc.select(".tiles_b18pwp01.core_po9665q"); 

            List<Offer> result = offerParser.parseAll(offers); 

            for (Offer offer : result) {
                try {
                    offerRepository.save(offer);
                } catch (DataIntegrityViolationException e) {
                    System.out.println(e);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Scraping failed: " + e.getMessage());
        }
    }
}
