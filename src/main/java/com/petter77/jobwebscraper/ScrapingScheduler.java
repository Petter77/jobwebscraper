package com.petter77.jobwebscraper;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ScrapingScheduler {

    private final ScrapingService scrapingService;
    public ScrapingScheduler(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @Value("${scraper.url}")
    private String scraperUrl;

    @Value("${scraper.css-selector.offer}")
    private String scraperCssSelectorOffer;

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void scrape() {
        scrapingService.scrapeAndSave();
    }
}
