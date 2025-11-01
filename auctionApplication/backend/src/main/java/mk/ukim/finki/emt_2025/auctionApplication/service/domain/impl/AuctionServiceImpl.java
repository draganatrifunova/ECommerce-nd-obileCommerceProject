package mk.ukim.finki.emt_2025.auctionApplication.service.domain.impl;

import mk.ukim.finki.emt_2025.auctionApplication.exceptions.*;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.Status;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.repository.AuctionRepository;
import mk.ukim.finki.emt_2025.auctionApplication.repository.ItemRepository;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.AuctionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final ItemRepository itemRepository;


    public AuctionServiceImpl(AuctionRepository auctionRepository, ItemRepository itemRepository) {
        this.auctionRepository = auctionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Auction findById(Long id) {
        return this.auctionRepository
                .findById(id)
                .orElseThrow(AuctionNotFoundException::new);
    }

    @Override
    public Auction findByIdAndOrganizer(Long id, User organizer) {
        return this.auctionRepository
                .findByIdAndOrganizer(id, organizer)
                .orElseThrow(AuctionNotFoundException::new);
    }

    @Override
    public List<Auction> findAll() {
        return this.auctionRepository
                .findAll();
    }

    @Override
    public Auction startByIdAndOrganizer(Long id, User organizer) {
        Auction auction = this.findByIdAndOrganizer(id, organizer);
        if(auction.getStatus() != Status.RESERVED){
            throw new AuctionStatusIsNotProper();
        }
        if(auction.getVisitors().isEmpty()){
            throw new AuctionDoNotHaveAnyVisitorException();
        }
        auction.startAuction();
        return this.auctionRepository.save(auction);
    }

    @Override
    public Auction cancelByIdAndOrganizer(Long id, User organizer) {
        Auction auction = this.findByIdAndOrganizer(id, organizer);
        System.out.println(auction.getStatus());
        if(auction.getStatus() != Status.RESERVED){
            throw new AuctionStatusIsNotProper();
        }

        Item i = auction.getItem();
        i.removeItemFromAuction();
        auction.calcelAuction();

        this.itemRepository.save(i);
        this.auctionRepository.save(auction);
        return auction;
    }

    @Override
    public Auction finishByIdAndOrganizer(Long id, User organizer) {
        Auction auction = this.findByIdAndOrganizer(id, organizer);

        if(auction.getStatus() != Status.STARTED){
            throw new AuctionStatusIsNotProper();
        }

        //items --> ovde ne se vrakaat da bidat available zatoa sto se veke prodadeni
        auction.finishAuction();
        return this.auctionRepository.save(auction);
    }

    @Override
    public User joinAuction(Long auctionId, User visitor) {

        Auction auction = this.auctionRepository
                .findById(auctionId)
                .orElseThrow(AuctionNotFoundException::new);

        if(auction.getStatus() != Status.RESERVED){
            throw new AuctionStatusIsNotProper();
        }

        if(Objects.equals(auction.getOrganizer().getId(), visitor.getId())){
            throw new AuctionOrganizerCanNotBeVisitor();
        }

        auction.getVisitors().add(visitor);
        this.auctionRepository.save(auction);

        return visitor;
    }

    @Override
    public Auction lastUserOffer(Long auctionId, int lastPrice, String username) {
        Auction auction = this.findById(auctionId);

        if(lastPrice > auction.getLastPriceOffered()){
            auction.setLastUserOffered(username);
            auction.setLastPriceOffered(lastPrice);
            this.auctionRepository.save(auction);
        }
        return auction;
    }
}
