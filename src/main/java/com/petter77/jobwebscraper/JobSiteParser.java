package com.petter77.jobwebscraper;
import java.io.IOException;
import java.util.List;

public interface JobSiteParser {
    public List<Offer> parse() throws IOException;
    public String getSiteName();
}
