package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserDto(Long id, String name, String surname, String username) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(user.getId(), user.getName(), user.getSurname(), user.getUsername());
    }

    public static List<DisplayUserDto> from(List<User> users){
        return users
                .stream()
                .map(DisplayUserDto::from)
                .collect(Collectors.toList());
    }
}
