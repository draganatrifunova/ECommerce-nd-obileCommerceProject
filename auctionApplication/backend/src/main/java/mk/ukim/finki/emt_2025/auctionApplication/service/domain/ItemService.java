package mk.ukim.finki.emt_2025.auctionApplication.service.domain;

import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.util.List;

public interface ItemService {
    Item findById(Long id);
    Item addItem(Item item);
    Item updateItem(Long itemId, Item item);

    List<Item> listAllItems();

    void deleteItem(Long itemId);
    Auction createAuction(Item item, User organizer);
}
