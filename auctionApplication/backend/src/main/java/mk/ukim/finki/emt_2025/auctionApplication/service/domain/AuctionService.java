package mk.ukim.finki.emt_2025.auctionApplication.service.domain;

import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;

import java.util.List;

public interface AuctionService {

    Auction findById(Long id);
    Auction findByIdAndOrganizer(Long id, User organizer);

    List<Auction> findAll();

    Auction startByIdAndOrganizer(Long id, User organizer);
    Auction cancelByIdAndOrganizer(Long id, User organizer);
    Auction finishByIdAndOrganizer(Long id, User organizer);

    User joinAuction(Long auctionId, User visitor);

    //samo organizer moze da ja startuva aukcijata, da ja otrkaze ili da ja zavrse
    //zatoa imame metod findByIdAndOrganizer :)

}
