package mk.ukim.finki.emt_2025.auctionApplication.service.domain.impl;

import mk.ukim.finki.emt_2025.auctionApplication.exceptions.*;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Status;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.repository.AuctionRepository;
import mk.ukim.finki.emt_2025.auctionApplication.repository.UserRepository;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuctionRepository auctionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ovoa e popravilo registriranje na User, userRegister
    @Override
    public User addUser(User user) {
        Optional<User> uu = this.listUsers()
                .stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();

        if(uu.isPresent()){
            throw new UsernameMustBeUniqueException();
        }

        User newUser = new User(user.getName(), user.getSurname(), user.getUsername(), passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(newUser);
    }

    @Override
    public List<User> listUsers() {
        return this.userRepository
                .findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        User u = this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        Optional<User> uu = this.listUsers()
                .stream()
                .filter(uuu -> ( (!uuu.getId().equals(id)) && uuu.getUsername().equals(user.getUsername())) )
                .findFirst();

        if(uu.isPresent()){
            throw new UsernameMustBeUniqueException();
        }

        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setUsername(user.getUsername());
        if(!user.getPassword().isEmpty()){
            u.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return this.userRepository.save(u);
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);


        this.userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void addVisitor(Long visitorId, Long auctionId) {
        User visitor = this.findById(visitorId);
        Auction auction = this.auctionRepository
                .findById(auctionId)
                .orElseThrow(AuctionNotFoundException::new);

        if(auction.getStatus() != Status.RESERVED){
            throw new AuctionStatusIsNotProper();
        }

        if(Objects.equals(auction.getOrganizer().getId(), visitorId)){
            throw new AuctionOrganizerCanNotBeVisitor();
        }

        auction.getVisitors().add(visitor);
        this.auctionRepository.save(auction);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository
                .findByUsername(username);
    }
    @Override
    public User login(String username, String password) {
        User user = findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IncorrectPasswordException();
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
