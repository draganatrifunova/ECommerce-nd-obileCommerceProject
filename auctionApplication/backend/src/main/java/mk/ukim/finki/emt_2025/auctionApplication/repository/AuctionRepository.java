package mk.ukim.finki.emt_2025.auctionApplication.repository;

import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Optional<Auction> findByIdAndOrganizer(Long id, User organizer);
}
