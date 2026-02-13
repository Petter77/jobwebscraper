package com.petter77.jobwebscraper;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.concurrent.TimeUnit;

@Component
public class ScrapingScheduler {

    private final ScrapingService scrapingService;
    public ScrapingScheduler(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void scrape() {
        scrapingService.scrapeAndSave();
    }
}
