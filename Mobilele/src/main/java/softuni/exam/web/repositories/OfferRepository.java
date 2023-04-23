package softuni.exam.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.web.domain.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
