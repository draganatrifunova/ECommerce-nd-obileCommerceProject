package mk.ukim.finki.emt_2025.auctionApplication.web.controllers;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.ItemApplicationService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.ItemService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemApplicationService itemApplicationService;
    private final ItemService itemService;


    public ItemController(ItemApplicationService itemApplicationService, ItemService itemService) {
        this.itemApplicationService = itemApplicationService;
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayItemDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.itemApplicationService
                .findById(id));
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DisplayItemDto> addItem(@RequestPart("item") CreateItemDto item,
                                                  @RequestPart("image") MultipartFile image) throws IOException {
        try {
            DisplayItemDto displayItemDto = this.itemApplicationService
                    .addItem(item, image);

            return ResponseEntity.ok(displayItemDto);
        } catch (Exception e) {
            throw new IOException();
        }
    }


    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getItemImage(@PathVariable Long id) {
        Item item = this.itemService
                .findById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(item.getImageType()))
                .body(item.getImageDate());
    }


    @PutMapping(value = "/update/{itemId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE

    )
    public ResponseEntity<DisplayItemDto> updateItem(@PathVariable Long itemId,
                                                     @RequestPart("item") CreateItemDto createItemDto,
                                                     @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(this.itemApplicationService
                .updateItem(itemId, createItemDto, image));
    }

    @GetMapping
    public ResponseEntity<List<DisplayItemDto>> listAllItems() {
        return ResponseEntity.ok(this.itemApplicationService
                .listAllItems());
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        this.itemApplicationService
                .deleteItem(itemId);

        return ResponseEntity
                .noContent().build();
    }

    @PostMapping("/{id}/createAuction")
    public ResponseEntity<DisplayAuctionDto> createAuction(@PathVariable Long id,
                                                           @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(DisplayAuctionDto.from(this
                .itemApplicationService
                .createAuction(id, user)));
    }
}
