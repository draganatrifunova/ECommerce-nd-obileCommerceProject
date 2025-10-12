package mk.ukim.finki.emt_2025.auctionApplication.service.application;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.util.List;

public interface AuctionApplicationService {

    DisplayAuctionDto findById(Long id);
    DisplayAuctionDto findByIdAndOrganizer(Long id, User organizer);

    List<DisplayAuctionDto> findAll();

    DisplayAuctionDto startByIdAndOrganizer(Long id, User organizer);
    DisplayAuctionDto cancelByIdAndOrganizer(Long id, User organizer);
    DisplayAuctionDto finishByIdAndOrganizer(Long id, User organizer);
}
