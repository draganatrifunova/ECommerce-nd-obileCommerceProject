package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.User;

public record RegisterUserRequestDto(
        String username,
        String password,
        String name,
        String surname
) {

    //public User(String name, String surname, String username, String password) {
    public User toUser(){
        return new User(name, surname, username, password);
    }
}
