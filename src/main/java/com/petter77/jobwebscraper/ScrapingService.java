package com.petter77.jobwebscraper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.springframework.dao.DataIntegrityViolationException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;

@Service
public class ScrapingService {

    private static final Logger log = LoggerFactory.getLogger(ScrapingService.class);

    private final OfferParser offerParser;
    private final OfferRepository offerRepository;

    @Value("${scraper.url}")
    private String scraperUrl;

    @Value("${scraper.css-selector.offer}")
    private String offerSelector;

    public ScrapingService(OfferParser offerParser, OfferRepository offerRepository) {
        this.offerParser = offerParser;
        this.offerRepository = offerRepository;
    }

    public void scrapeAndSave() {
        try {
            Document doc = Jsoup.connect(scraperUrl).get();
            Elements offers = doc.select(offerSelector);

            List<Offer> parsed = offerParser.parseAll(offers);

            for (Offer offer : parsed) {
                try {
                    offerRepository.save(offer);
                } catch (DataIntegrityViolationException e) {
                    log.debug("Duplicate offer: {}", offer.getUrl());
                }
            }

            log.info("Scraped {} offers", parsed.size());
        } catch (Exception e) {
            log.error("Scraping failed", e);
        }
    }
}
