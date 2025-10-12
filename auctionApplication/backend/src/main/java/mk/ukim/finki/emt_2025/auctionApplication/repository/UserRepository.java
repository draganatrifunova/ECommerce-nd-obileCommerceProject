package mk.ukim.finki.emt_2025.auctionApplication.repository;

import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
