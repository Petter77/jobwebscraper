package com.petter77.jobwebscraper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@RestController
@RequestMapping("/api/offers")
public class OfferController {
    private final OfferRepository offerRepository;

    OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    @GetMapping("/search")
    public List<Offer> searchByTechnology(@RequestParam String technology) {
        return  offerRepository.findByTechnologiesContainingIgnoreCase(technology);
    }


}
