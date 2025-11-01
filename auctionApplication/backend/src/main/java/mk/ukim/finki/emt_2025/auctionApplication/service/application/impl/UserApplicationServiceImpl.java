package mk.ukim.finki.emt_2025.auctionApplication.service.application.impl;

import mk.ukim.finki.emt_2025.auctionApplication.dto.LoginUserRequestDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.LoginUserResponseDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserRequestDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserResponseDto;
import mk.ukim.finki.emt_2025.auctionApplication.exceptions.UserNotFoundException;
import mk.ukim.finki.emt_2025.auctionApplication.helpers.JwtHelper;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.UserApplicationService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto) {
        return RegisterUserResponseDto.from(this.userService
                .addUser(registerUserRequestDto.toUser()));
    }

    @Override
    public List<RegisterUserResponseDto> listUsers() {
        return RegisterUserResponseDto.from(this.userService
                .listUsers());
    }

    @Override
    public RegisterUserResponseDto updateUser(Long id, RegisterUserRequestDto createUserDto) {
        return RegisterUserResponseDto.from(this.userService
                .updateUser(id, createUserDto.toUser()));
    }

    //samo ADMIN da moze da izbrise user
    @Override
    public void deleteUser(Long id) {
        this.userService
                .deleteUser(id);
    }

    @Override
    public RegisterUserResponseDto findById(Long id) {
        return RegisterUserResponseDto.from(this
                .userService
                .findById(id));
    }

    @Override
    public void addVisitor(Long visitorId, Long auctionId) {
        this.userService
                .addVisitor(visitorId, auctionId);
    }

    @Override
    public LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto) {
        User user = this.userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());
        String token = jwtHelper.generateToken(user);

        LoginUserResponseDto loginUserResponseDto = new LoginUserResponseDto(token);
        return loginUserResponseDto;
    }

    @Override
    public RegisterUserResponseDto findByUsername(String username) {
        return RegisterUserResponseDto.from(this.userService
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new));
    }
}
