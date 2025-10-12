package mk.ukim.finki.emt_2025.auctionApplication.web;

import mk.ukim.finki.emt_2025.auctionApplication.dto.CreateUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.DisplayUserDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.Auction;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserApplicationService userApplicationService;


    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayUserDto> addUser(@RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(this
                .userApplicationService
                .addUser(createUserDto));
    }

    @GetMapping
    public ResponseEntity<List<DisplayUserDto>> listUsers(){
        return ResponseEntity.ok(this.userApplicationService
                .listUsers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DisplayUserDto> updateUser(@PathVariable Long id,
                                                     @RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(this.userApplicationService
                .updateUser(id, createUserDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.userApplicationService
                .deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayUserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.userApplicationService
                .findById(id));
    }

    @PostMapping("/{visitorId}/addVisitor")
    public ResponseEntity<Void> addVisitor(@PathVariable Long visitorId,
                           @RequestParam Long auctionId) {

        this.userApplicationService
                .addVisitor(visitorId, auctionId);

        return ResponseEntity
                .noContent().build();
    }
}
