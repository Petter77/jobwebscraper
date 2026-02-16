# Job Web Scraper

Spring Boot application that automatically scrapes IT job offers from Polish job boards and stores them in a PostgreSQL database. Provides a REST API for browsing and filtering collected offers.

## Features

- Scrapes multiple job boards: Pracuj.pl, NoFluffJobs, JustJoinIT
- Extensible parser architecture - add a new site by implementing one short class
- Automated scraping with configurable schedule (default: every 30 minutes)
- Duplicate detection based on offer URL
- REST API with technology-based filtering
- Externalized CSS selectors - adapts to site changes without code modifications

## Tech Stack

- Java 21
- Spring Boot 4.0
- Spring Data JPA / Hibernate
- PostgreSQL
- Jsoup (HTML parsing)
- Lombok
- SLF4J (logging)

## Getting Started

### Prerequisites

- Java 21+
- PostgreSQL with a database named `jobwebscraper`
- Maven 3.9+

### Configuration

Set the required environment variable before running:

```bash
export DB_USERNAME=your_postgres_username
```

Database connection and scraper settings are configured in `src/main/resources/application.properties`.

### Running

```bash
./mvnw spring-boot:run
```

The scraper will execute immediately on startup and then repeat on the configured schedule.

## API Endpoints

### Get all offers

```
GET /api/offers
```

### Search by technology

```
GET /api/offers/search?technology=java
```

Returns all offers containing the specified technology (case-insensitive).

## Project Structure

```
src/main/java/com/petter77/jobwebscraper/
├── JobwebscraperApplication.java           # Entry point, enables scheduling
├── model/
│   └── Offer.java                          # JPA entity
├── repository/
│   └── OfferRepository.java                # Spring Data repository
├── parser/
│   ├── JobSiteParser.java                  # Parser interface
│   ├── AbstractJobSiteParser.java          # Shared parsing logic
│   ├── PracujPlParser.java                 # Pracuj.pl implementation
│   ├── NoFluffJobsParser.java              # NoFluffJobs implementation
│   └── JustJoinItParser.java               # JustJoinIT implementation
├── service/
│   └── ScrapingService.java                # Scraping orchestration
├── scheduler/
│   └── ScrapingScheduler.java              # Scheduled trigger
└── controller/
    └── OfferController.java                # REST API endpoints
```

## Adding a New Job Board

1. Add selectors to `application.properties`:
```properties
scraper.url.newsite=https://...
scraper.css-selector.newsite.offer=...
scraper.css-selector.newsite.title=...
scraper.css-selector.newsite.technologies=...
scraper.css-selector.newsite.url=...
```

2. Create a parser class:
```java
@Component
public class NewSiteParser extends AbstractJobSiteParser {

    public NewSiteParser(
            @Value("${scraper.url.newsite}") String url,
            @Value("${scraper.css-selector.newsite.offer}") String offer,
            @Value("${scraper.css-selector.newsite.title}") String title,
            @Value("${scraper.css-selector.newsite.technologies}") String technologies,
            @Value("${scraper.css-selector.newsite.url}") String urlSelector) {
        super(url, offer, title, technologies, urlSelector);
    }

    public String getSiteName() { return "newsite.com"; }
}
```

No other code changes required - Spring auto-discovers the new parser.

## Roadmap

- [x] Support for multiple job boards
- [x] Interface-based parser architecture
- [ ] Pagination and sorting on API endpoints
- [ ] Global error handling with `@ControllerAdvice`
- [ ] Swagger/OpenAPI documentation
- [ ] Unit and integration tests
- [ ] Docker + docker-compose setup

