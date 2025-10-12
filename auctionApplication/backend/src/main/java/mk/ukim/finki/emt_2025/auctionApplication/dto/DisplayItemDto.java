package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayItemDto(Long id, String name, int price, String imageUrl, boolean available) {
    public static DisplayItemDto from(Item item) {
        return new DisplayItemDto(
                item.getId(),
                item.getName(),
                item.getPrice(),
                "/api/items/" + item.getId() + "/image",
                item.isAvailable());
    }

    public static List<DisplayItemDto> from(List<Item> items) {
        return items.stream()
                .map(DisplayItemDto::from)
                .collect(Collectors.toList());
    }
}