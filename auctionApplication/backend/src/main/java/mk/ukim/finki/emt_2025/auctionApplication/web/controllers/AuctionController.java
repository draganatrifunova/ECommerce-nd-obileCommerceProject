package mk.ukim.finki.emt_2025.auctionApplication.web.controllers;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateLastUserOfferDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayLastUserOfferDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserResponseDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.AuctionApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {
    private final AuctionApplicationService auctionApplicationService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public AuctionController(AuctionApplicationService auctionApplicationService, SimpMessagingTemplate simpMessagingTemplate) {
        this.auctionApplicationService = auctionApplicationService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @GetMapping("/{id}/organizer")
    public ResponseEntity<DisplayAuctionDto> findByIdAndOrganizer(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.auctionApplicationService
                .findByIdAndOrganizer(id, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuctionDto> findById(@PathVariable Long id) {
        DisplayAuctionDto dto = this.auctionApplicationService
                .findById(id);

        simpMessagingTemplate.convertAndSend("/topic/auctions/" + id, dto);
        return ResponseEntity.ok(dto);
        //return ResponseEntity.ok(this.auctionApplicationService
        //.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DisplayAuctionDto>> findAll() {
        return ResponseEntity.ok(this.auctionApplicationService
                .findAll());
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<DisplayAuctionDto> startByIdAndOrganizer(@PathVariable Long id, @AuthenticationPrincipal User user) {
        DisplayAuctionDto dto = this.auctionApplicationService
                .startByIdAndOrganizer(id, user);

        simpMessagingTemplate.convertAndSend("/topic/auctions/" + id, dto);

        return ResponseEntity.ok(dto);

        //return ResponseEntity.ok(this.auctionApplicationService
        //.startByIdAndOrganizer(id, user));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<DisplayAuctionDto> cancelByIdAndOrganizer(@PathVariable Long id, @AuthenticationPrincipal User user) {
        DisplayAuctionDto dto = this.auctionApplicationService
                .cancelByIdAndOrganizer(id, user);

        simpMessagingTemplate.convertAndSend("/topic/auctions/" + id, dto);

        return ResponseEntity.ok(dto);

        //return ResponseEntity.ok(this.auctionApplicationService
        //.cancelByIdAndOrganizer(id, user));
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<DisplayAuctionDto> finishByIdAndOrganizer(@PathVariable Long id, @AuthenticationPrincipal User user) {
        DisplayAuctionDto dto = this.auctionApplicationService
                .finishByIdAndOrganizer(id, user);

        simpMessagingTemplate.convertAndSend("/topic/auctions/" + id, dto);

        return ResponseEntity.ok(dto);

        // return ResponseEntity.ok(this.auctionApplicationService
        //   .finishByIdAndOrganizer(id, user));
    }

    @PostMapping("/{auctionId}/addVisitor")
    public ResponseEntity<RegisterUserResponseDto> joinAuction(@PathVariable Long auctionId,
                                                               @AuthenticationPrincipal User user) {

        RegisterUserResponseDto dto = this.auctionApplicationService
                .joinAuction(auctionId, user);

        simpMessagingTemplate.convertAndSend("/topic/visitors/" + user.getUsername(), dto);
        return ResponseEntity.ok(dto);
        //return ResponseEntity.ok(this.auctionApplicationService
        //.joinAuction(auctionId, user));

    }

    @PostMapping("/{id}/addOffer")
    public ResponseEntity<DisplayLastUserOfferDto> lastUserOffer(@PathVariable Long id,
                                                                 @RequestBody CreateLastUserOfferDto offer,
                                                                 @AuthenticationPrincipal User user) {

        DisplayLastUserOfferDto dto = this.auctionApplicationService
                .lastUserOffer(id, offer, user);
        simpMessagingTemplate.convertAndSend("/topic/offers/" + id, dto);
        return ResponseEntity.ok(dto);
        //return ResponseEntity.ok(this.auctionApplicationService
        //     .lastUserOffer(id, offer, user));
    }

    @GetMapping("/{id}/lastOffer")
    public ResponseEntity<DisplayLastUserOfferDto> getLastOffer(@PathVariable Long id) {

        DisplayLastUserOfferDto dto = this.auctionApplicationService
                .getLastOffer(id);

        simpMessagingTemplate.convertAndSend("/topic/offers/" + id, dto);
        return ResponseEntity.ok(dto);
    }
}
