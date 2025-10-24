package mk.ukim.finki.emt_2025.auctionApplication.service.domain.impl;

import mk.ukim.finki.emt_2025.auctionApplication.exceptions.AuctionStatusIsNotProper;
import mk.ukim.finki.emt_2025.auctionApplication.exceptions.ItemIsNotAvailableException;
import mk.ukim.finki.emt_2025.auctionApplication.exceptions.ItemNotFoundException;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.Status;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.repository.AuctionRepository;
import mk.ukim.finki.emt_2025.auctionApplication.repository.ItemRepository;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final AuctionRepository auctionRepository;

    public ItemServiceImpl(ItemRepository itemRepository, AuctionRepository auctionRepository) {
        this.itemRepository = itemRepository;
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Item findById(Long id) {
        return this.itemRepository
                .findById(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public Item addItem(Item item) {
        return this.itemRepository
                .save(item);
    }

    @Override
    public Item updateItem(Long itemId, Item item) {
        Item i = this.findById(itemId);

        i.setName(item.getName());
        i.setPrice(item.getPrice());

        if(!(item.getImageName().isEmpty() && item.getImageType().isEmpty() && item.getImageDate()==null)){
            i.setImageName(item.getImageName());
            i.setImageDate(item.getImageDate());
            i.setImageType(item.getImageType());
        }
        return this.itemRepository.save(i);
    }

    @Override
    public List<Item> listAllItems() {
        return this.itemRepository
                .findAll();
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = this.findById(itemId);
        this.itemRepository.delete(item);
    }

    @Override
    public Auction createAuction(Item item, User organizer) {
        if(!item.isAvailable()){
            throw new ItemIsNotAvailableException();
        }
        item.putItemInAuction();
        this.itemRepository.save(item);
        Auction auction = new Auction(organizer, item);
        return this.auctionRepository.save(auction);
    }

}
