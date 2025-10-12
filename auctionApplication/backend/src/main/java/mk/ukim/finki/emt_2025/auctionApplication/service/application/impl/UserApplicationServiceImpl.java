package mk.ukim.finki.emt_2025.auctionApplication.service.application.impl;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.UserApplicationService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public DisplayUserDto addUser(CreateUserDto createUserDto) {
        return DisplayUserDto.from(this.userService
                .addUser(createUserDto.toUser()));
    }

    @Override
    public List<DisplayUserDto> listUsers() {
        return DisplayUserDto.from(this.userService
                .listUsers());
    }

    @Override
    public DisplayUserDto updateUser(Long id, CreateUserDto createUserDto) {
        return DisplayUserDto.from(this.userService
                .updateUser(id, createUserDto.toUser()));
    }

    @Override
    public void deleteUser(Long id) {
        this.userService
                .deleteUser(id);
    }

    @Override
    public DisplayUserDto findById(Long id) {
        return DisplayUserDto.from(this.userService
                .findById(id));
    }

    @Override
    public void addVisitor(Long visitorId, Long auctionId) {
        this.userService
                .addVisitor(visitorId, auctionId);
    }
}
