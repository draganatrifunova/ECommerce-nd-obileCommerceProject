package mk.ukim.finki.emt_2025.auctionApplication.dto;

import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;

public record DisplayLastUserOfferDto(
        Long auctionId,
        String lastUsername,
        int lastPrice) {

    public static DisplayLastUserOfferDto from(Auction auction){
        return new DisplayLastUserOfferDto(
                auction.getId(),
                auction.getLastUserOffered(),
                auction.getLastPriceOffered()
        );
    }
}
