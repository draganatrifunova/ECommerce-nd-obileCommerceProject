package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.Role;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.util.List;
import java.util.stream.Collectors;

//public User(String name, String surname, String username, String password, Role role) {
public record RegisterUserResponseDto(
        Long id,
        String username,
        String name,
        String surname,
        Role role
) {
    public static RegisterUserResponseDto from(User user){
        return new RegisterUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public static List<RegisterUserResponseDto> from(List<User> users) {
        return users
                .stream()
                .map(RegisterUserResponseDto::from)
                .collect(Collectors.toList());
    }
}
