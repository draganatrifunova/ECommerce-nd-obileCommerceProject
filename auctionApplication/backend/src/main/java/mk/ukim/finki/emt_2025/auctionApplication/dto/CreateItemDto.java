package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.Item;

import java.util.SplittableRandom;

//String name, int price, String imageUrl
public record CreateItemDto(String name, int price) {
    public Item toItem(String imageName, String imageType, byte[] imageData){
        return new Item(name, price, imageName, imageType, imageData);
    }

}
