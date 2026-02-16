package com.petter77.jobwebscraper.service;

import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;
import com.petter77.jobwebscraper.parser.JobSiteParser;
import com.petter77.jobwebscraper.repository.OfferRepository;
import com.petter77.jobwebscraper.model.Offer;


@Service
public class ScrapingService {

    private static final Logger log = LoggerFactory.getLogger(ScrapingService.class);

    private final List<JobSiteParser> parsers;
    private final OfferRepository offerRepository;

    public ScrapingService(List<JobSiteParser> parsers, OfferRepository offerRepository) {
        this.parsers = parsers;
        this.offerRepository = offerRepository;
    }

    public void scrapeAndSave() {
        for (JobSiteParser parser : parsers) {
            int scrapedCounter = 0;
            try {
                List<Offer> parsed = parser.parse();
                for (Offer offer : parsed) {
                    try {
                        offerRepository.save(offer);
                        scrapedCounter++;
                    } catch (DataIntegrityViolationException e) {
                        log.debug("Duplicate offer: {}", offer.getUrl());
                    }
                }

                log.info("Scraped {} offers from {}", scrapedCounter, parser.getSiteName());
            } catch (Exception e) {
                log.error("Scraping failed", e);
            }

        }
    }
}
