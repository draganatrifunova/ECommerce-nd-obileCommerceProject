package mk.ukim.finki.emt_2025.auctionApplication.web.controllers;

import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayAuctionDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserResponseDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.AuctionApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {
    private final AuctionApplicationService auctionApplicationService;

    public AuctionController(AuctionApplicationService auctionApplicationService) {
        this.auctionApplicationService = auctionApplicationService;
    }


    @GetMapping("/{id}/organizer")
    public ResponseEntity<DisplayAuctionDto> findByIdAndOrganizer(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.auctionApplicationService
                .findByIdAndOrganizer(id, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuctionDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.auctionApplicationService
                .findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DisplayAuctionDto>> findAll() {
        return ResponseEntity.ok(this.auctionApplicationService
                .findAll());
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<DisplayAuctionDto> startByIdAndOrganizer(@PathVariable Long id,  @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.auctionApplicationService
                .startByIdAndOrganizer(id, user));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<DisplayAuctionDto> cancelByIdAndOrganizer(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.auctionApplicationService
                .cancelByIdAndOrganizer(id, user));
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<DisplayAuctionDto> finishByIdAndOrganizer(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.auctionApplicationService
                .finishByIdAndOrganizer(id, user));
    }

    @PostMapping("/{auctionId}/addVisitor")
    public ResponseEntity<RegisterUserResponseDto> joinAuction(@PathVariable Long auctionId,
                                                               @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.auctionApplicationService
                .joinAuction(auctionId, user));

    }
}
