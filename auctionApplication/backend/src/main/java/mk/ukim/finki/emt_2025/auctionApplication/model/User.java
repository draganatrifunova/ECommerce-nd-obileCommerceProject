package mk.ukim.finki.emt_2025.auctionApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name = "app_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username mustn't be blank")
    private String username;

    @NotBlank(message = "name mustn't be blank")
    private String name;

    @NotBlank(message = "surname mustn't be blank")
    private String surname;

    public User(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public User() {

    }
}
