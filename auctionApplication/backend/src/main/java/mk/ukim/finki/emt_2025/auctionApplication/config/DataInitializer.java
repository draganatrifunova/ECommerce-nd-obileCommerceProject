package mk.ukim.finki.emt_2025.auctionApplication.config;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.Role;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.repository.ItemRepository;
import mk.ukim.finki.emt_2025.auctionApplication.repository.UserRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class DataInitializer {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, ItemRepository itemRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //User(String name, String surname, String username, String password) {
    @PostConstruct
    public void init() throws IOException {
        User user1 = new User("Dragana", "Trifunova", "dtrifunova", passwordEncoder.encode("dtrifunova"), Role.ROLE_ADMIN);
        User user2 = new User("Ana", "De Armas", "armas", passwordEncoder.encode("armas"), Role.ROLE_CUSTOMER);
        User user3 = new User("Ben", "Affleck", "baffleck", passwordEncoder.encode("baffleck"), Role.ROLE_OWNER);
        User user4 = new User("Tom", "Cruise", "tcruise", passwordEncoder.encode("tcruise"), Role.ROLE_CUSTOMER);

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.save(user4);

        //////////////////////////////////////////////////////////////////////

        byte[] carBytes = readImage("static/images/car.jpg");
        byte[] fieldBytes = readImage("static/images/field.jpg");
        byte[] realEstateBytes = readImage("static/images/realEstate.jpg");
        byte[] flatBytes = readImage("static/images/flat.jpg");

        Item item1 = new Item("Car", 60000, "car.jpg", "image/jpeg", carBytes);
        Item item2 = new Item("Field", 80000, "field.jpg", "image/jpeg", fieldBytes);
        Item item3 = new Item("Real Estate", 600000, "realEstate.jpg", "image/jpeg", realEstateBytes);
        Item item4 = new Item("Flat", 190000, "flat.jpg", "image/jpeg", flatBytes);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
    }

    private byte[] readImage(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return Files.readAllBytes(resource.getFile().toPath());
    }

}
