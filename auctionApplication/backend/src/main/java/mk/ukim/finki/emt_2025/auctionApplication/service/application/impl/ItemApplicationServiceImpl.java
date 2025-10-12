package mk.ukim.finki.emt_2025.auctionApplication.service.application.impl;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.ItemApplicationService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.AuctionService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.ItemService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ItemApplicationServiceImpl implements ItemApplicationService {

    private final ItemService itemService;
    private final UserService userService;
    private final AuctionService auctionService;



    public ItemApplicationServiceImpl(ItemService itemService, UserService userService, AuctionService auctionService) {
        this.itemService = itemService;
        this.userService = userService;
        this.auctionService = auctionService;
    }

    @Override
    public DisplayItemDto findById(Long id) {
        return DisplayItemDto.from(this.itemService
                .findById(id));
    }

    @Override
    public DisplayItemDto addItem(CreateItemDto createItemDto, MultipartFile image) throws IOException {

       String imageFile = image.getOriginalFilename();
       String imageType = image.getContentType();
       byte[] imageData = image.getBytes();

        return DisplayItemDto.from(this.itemService
                .addItem(createItemDto.toItem(imageFile, imageType, imageData)));
    }

    @Override
    public DisplayItemDto updateItem(Long itemId, CreateItemDto createItemDto, MultipartFile image) throws IOException {
        String imageFile = "";
        String imageType = "";
        byte[] imageData = null;

        if(image != null){
            imageFile = image.getOriginalFilename();
            imageType = image.getContentType();
            imageData = image.getBytes();
        }

        return DisplayItemDto.from(this.itemService
                .updateItem(itemId, createItemDto.toItem(imageFile, imageType, imageData)));
    }

    @Override
    public List<DisplayItemDto> listAllItems() {
        return DisplayItemDto.from(this.itemService
                .listAllItems());
    }

    @Override
    public void deleteItem(Long itemId) {
        this.itemService
                .deleteItem(itemId);
    }

    @Override
    public Auction createAuction(Long id, Long organizerId) {
        Item item = this.itemService.findById(id);
        User organizer = this.userService.findById(organizerId);

        return this.itemService
                .createAuction(item, organizer);
    }

    @Override
    public Item addItemToAuction(Long id, Long auctionId) {
        Item item = this.itemService.findById(id);
        Auction auction = this.auctionService
                .findById(auctionId);

        return this.itemService
                .addItemToAuction(item, auction);
    }
}
