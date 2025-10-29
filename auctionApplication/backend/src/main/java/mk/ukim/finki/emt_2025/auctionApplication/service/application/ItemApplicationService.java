package mk.ukim.finki.emt_2025.auctionApplication.service.application;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemApplicationService {
    DisplayItemDto findById(Long id);

    DisplayItemDto addItem(CreateItemDto item, MultipartFile image) throws IOException;
    DisplayItemDto updateItem(Long itemId, CreateItemDto createItemDto, MultipartFile image) throws IOException;

    List<DisplayItemDto> listAllItems();

    void deleteItem(Long itemId);

    Auction createAuction(Long id, User organizer);
}
