package com.petter77.jobwebscraper;

import java.util.List;

public interface JobSiteParser {
    public List<Offer> parse();
    public String getSiteName();
}
