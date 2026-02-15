package com.petter77.jobwebscraper.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.petter77.jobwebscraper.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByTechnologiesContainingIgnoreCase(String technology);

}
