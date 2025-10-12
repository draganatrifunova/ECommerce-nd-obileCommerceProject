package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.User;

//String name, String surname)
public record CreateUserDto(String name, String surname, String username) {
    public User toUser() {
        return new User(name, surname, username);
    }
}