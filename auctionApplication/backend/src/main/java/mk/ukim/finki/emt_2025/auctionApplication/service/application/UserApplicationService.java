package mk.ukim.finki.emt_2025.auctionApplication.service.application;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;

import java.util.List;

public interface UserApplicationService {
    DisplayUserDto addUser(CreateUserDto createUserDto);

    List<DisplayUserDto> listUsers();

    DisplayUserDto updateUser(Long id, CreateUserDto createUserDto);

    void deleteUser(Long id);

    DisplayUserDto findById(Long id);
    void addVisitor(Long visitorId, Long auctionId);

}
