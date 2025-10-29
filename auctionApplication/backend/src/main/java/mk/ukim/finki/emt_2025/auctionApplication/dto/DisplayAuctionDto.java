package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.Status;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuctionDto(Long id,
                                LocalDateTime timeStarting,
                                LocalDateTime timeFinishing,
                                Status status,
                                Long item_id,
                                Long organizer_id,
                                List<String> visitors_username,
                                String itemName,
                                String organizerName,
                                String organizerSurname,
                                String organizerUsername) {

    public static DisplayAuctionDto from(Auction auction) {
        return new DisplayAuctionDto(
                auction.getId(),
                auction.getTimeStarting(),
                auction.getTimeFinishing(),
                auction.getStatus(),
                auction.getItem().getId(),
                auction.getOrganizer().getId(),
                auction.getVisitors()
                        .stream().map(User::getUsername)
                        .collect(Collectors.toList()),
                auction.getItem().getName(),
                auction.getOrganizer().getName(),
                auction.getOrganizer().getSurname(),
                auction.getOrganizer().getUsername()
        );
    }


    public static List<DisplayAuctionDto> from(List<Auction> auctions) {
        return auctions
                .stream()
                .map(DisplayAuctionDto::from)
                .collect(Collectors.toList());
    }

}
