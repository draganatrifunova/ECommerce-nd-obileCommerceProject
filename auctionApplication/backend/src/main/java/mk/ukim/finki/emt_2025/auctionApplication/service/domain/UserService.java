package mk.ukim.finki.emt_2025.auctionApplication.service.domain;

import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> listUsers();

    User updateUser(Long id, User user);
    void deleteUser(Long id);

    User findById(Long id);
    void addVisitor(Long visitorId, Long auctionId);
}
