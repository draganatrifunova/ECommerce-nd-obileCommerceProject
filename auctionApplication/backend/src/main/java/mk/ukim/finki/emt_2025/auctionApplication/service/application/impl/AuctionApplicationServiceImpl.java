package mk.ukim.finki.emt_2025.auctionApplication.service.application.impl;


import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayItemDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.model.Item;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.AuctionApplicationService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.AuctionService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.ItemService;
import mk.ukim.finki.emt_2025.auctionApplication.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionApplicationServiceImpl implements AuctionApplicationService {

    private final AuctionService auctionService;


    public AuctionApplicationServiceImpl(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @Override
    public DisplayAuctionDto findById(Long id) {
        return DisplayAuctionDto.from(this.auctionService
                .findById(id));
    }

    @Override
    public DisplayAuctionDto findByIdAndOrganizer(Long id, User organizer) {
        return DisplayAuctionDto.from(this.auctionService
                .findByIdAndOrganizer(id, organizer));

    }

    @Override
    public List<DisplayAuctionDto> findAll() {
        return DisplayAuctionDto.from(this.auctionService
                .findAll());
    }

    @Override
    public DisplayAuctionDto startByIdAndOrganizer(Long id, User organizer) {
        return DisplayAuctionDto
                .from(this.auctionService
                .startByIdAndOrganizer(id, organizer));
    }

    @Override
    public DisplayAuctionDto cancelByIdAndOrganizer(Long id, User organizer) {
        return DisplayAuctionDto.from(this.auctionService
                .cancelByIdAndOrganizer(id, organizer));
    }

    @Override
    public DisplayAuctionDto finishByIdAndOrganizer(Long id, User organizer) {
        return DisplayAuctionDto.from(this.auctionService
                .finishByIdAndOrganizer(id, organizer));
    }
}
