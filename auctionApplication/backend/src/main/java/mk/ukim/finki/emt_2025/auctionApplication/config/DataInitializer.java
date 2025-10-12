package mk.ukim.finki.emt_2025.auctionApplication.config;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.repository.ItemRepository;
import mk.ukim.finki.emt_2025.auctionApplication.repository.UserRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class DataInitializer {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public DataInitializer(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        User user1 = new User("Dragana", "Trifunova", "dtrifunova");
        User user2 = new User("Ana", "De Armas", "armas");
        User user3 = new User("Ben", "Affleck", "baffleck");

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);

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
