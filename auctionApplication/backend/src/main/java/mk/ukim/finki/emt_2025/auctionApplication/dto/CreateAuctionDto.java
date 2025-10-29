package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record CreateAuctionDto(Long item_id,
                               Long organizer_id) {

    public Auction toAuction(User organizer, Item item) {
        return new Auction(organizer, item);
    }

}
