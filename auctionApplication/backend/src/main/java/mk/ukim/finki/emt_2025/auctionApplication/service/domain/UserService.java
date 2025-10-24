package mk.ukim.finki.emt_2025.auctionApplication.service.domain;

import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //dodavanjeto User e vsusnost registriranje ne User
    User addUser(User user);
    List<User> listUsers();

    User updateUser(Long id, User user);
    void deleteUser(Long id);

    User findById(Long id);

    // na krajo ke treba da se izbrise addVisitor()
    void addVisitor(Long visitorId, Long auctionId);

    Optional<User> findByUsername(String username);

    User login(String username, String password);

    UserDetails loadUserByUsername(String username);
}
