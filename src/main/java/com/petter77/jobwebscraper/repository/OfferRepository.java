package com.petter77.jobwebscraper.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petter77.jobwebscraper.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Page<Offer> findByTechnologiesContainingIgnoreCase(String technology, Pageable pageable);
}
