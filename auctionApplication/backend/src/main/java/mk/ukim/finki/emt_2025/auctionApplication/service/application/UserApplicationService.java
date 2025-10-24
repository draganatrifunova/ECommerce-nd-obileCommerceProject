package mk.ukim.finki.emt_2025.auctionApplication.service.application;

import mk.ukim.finki.emt_2025.auctionApplication.dto.LoginUserRequestDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.LoginUserResponseDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserRequestDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto);

    List<RegisterUserResponseDto> listUsers();

    RegisterUserResponseDto updateUser(Long id, RegisterUserRequestDto registerUserRequestDto);

    void deleteUser(Long id);

    RegisterUserResponseDto findById(Long id);

    //da go brisam
    void addVisitor(Long visitorId, Long auctionId);

    LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto);
}
