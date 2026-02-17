package com.petter77.jobwebscraper.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.petter77.jobwebscraper.repository.OfferRepository;
import com.petter77.jobwebscraper.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/offers")
public class OfferController {
    private final OfferRepository offerRepository;

    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping
    public Page<Offer> getAll(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    @GetMapping("/search")
    public Page<Offer> searchByTechnology(@RequestParam String technology, Pageable pageable) {
        return  offerRepository.findByTechnologiesContainingIgnoreCase(pageable, technology);
    }


}
