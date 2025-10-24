package mk.ukim.finki.emt_2025.auctionApplication.web.controllers;

import mk.ukim.finki.emt_2025.auctionApplication.dto.LoginUserRequestDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.LoginUserResponseDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserRequestDto;
import mk.ukim.finki.emt_2025.auctionApplication.dto.RegisterUserResponseDto;
import mk.ukim.finki.emt_2025.auctionApplication.model.User;
import mk.ukim.finki.emt_2025.auctionApplication.service.application.UserApplicationService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserApplicationService userApplicationService;


    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    /*
    @GetMapping("/{username}")
    public ResponseEntity<RegisterUserResponseDto> findByUsername(@PathVariable String username){
        return ResponseEntity.ok(userApplicationService
                .findByUsername(username));
    }

     */

    @GetMapping("/me")
    public ResponseEntity<RegisterUserResponseDto> me(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(userApplicationService
                .findById(user.getId()));
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto registerUserRequestDto){
        return ResponseEntity.ok(this
                .userApplicationService
                .register(registerUserRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<RegisterUserResponseDto>> listUsers(){
        return ResponseEntity.ok(this.userApplicationService
                .listUsers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RegisterUserResponseDto> updateUser(@PathVariable Long id,
                                                     @RequestBody RegisterUserRequestDto registerUserRequestDto){
        return ResponseEntity.ok(this.userApplicationService
                .updateUser(id, registerUserRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.userApplicationService
                .deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterUserResponseDto> findById(@PathVariable Long id){
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

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody LoginUserRequestDto loginUserRequestDto){
        return ResponseEntity.ok(userApplicationService
                .login(loginUserRequestDto));
    }
}
