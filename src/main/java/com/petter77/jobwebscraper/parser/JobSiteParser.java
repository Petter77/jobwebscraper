package com.petter77.jobwebscraper.parser;
import java.io.IOException;
import java.util.List;
import com.petter77.jobwebscraper.model.Offer;

public interface JobSiteParser {
    public List<Offer> parse() throws IOException;
    public String getSiteName();
}
