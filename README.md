# Job Web Scraper

Spring Boot application that automatically scrapes IT job offers from Polish job boards and stores them in a PostgreSQL database. Provides a REST API for browsing and filtering collected offers.

## Features

- Automated scraping with configurable schedule (default: every 30 minutes)
- Duplicate detection based on offer URL
- REST API with technology-based filtering
- Externalized CSS selectors — adapts to site changes without code modifications

## Tech Stack

- Java 21
- Spring Boot 4.0
- Spring Data JPA / Hibernate
- PostgreSQL
- Jsoup (HTML parsing)
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
├── JobwebscraperApplication.java   # Entry point, enables scheduling
├── Offer.java                      # JPA entity
├── OfferRepository.java            # Spring Data repository
├── OfferParser.java                # HTML parsing logic
├── OfferController.java            # REST API endpoints
├── ScrapingService.java            # Scraping orchestration
└── ScrapingScheduler.java          # Scheduled trigger
```

## Roadmap

- [ ] Support for multiple job boards (interface-based parser architecture)
- [ ] Pagination and sorting on API endpoints
- [ ] Global error handling with `@ControllerAdvice`
- [ ] Swagger/OpenAPI documentation
- [ ] Unit and integration tests
- [ ] Docker + docker-compose setup

