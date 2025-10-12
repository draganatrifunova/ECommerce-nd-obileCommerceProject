package mk.ukim.finki.emt_2025.auctionApplication.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime timeStarting;
    private LocalDateTime timeFinishing;

    @Enumerated(EnumType.STRING)
    private Status status;

    //da validiram u servise slojo deka edna aukcija mora da ima barem eden Item
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();
    //cascade = CascadeType.ALL -->  Ova znaci deka koga ke izvrsite dejstvo
    //na aukciajta, toa ke se prenese na nejzinite items

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "auction_visitors",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> visitors;

    public Auction(User organizer, Item item) {
        this.organizer = organizer;
        status = Status.RESERVED;
        visitors = new ArrayList<>();
        this.items.add(item);
    }


    public Auction() {
        status = Status.RESERVED;
        visitors = new ArrayList<>();
    }

    public void startAuction(){
        this.timeStarting = LocalDateTime.now();
        status = Status.STARTED;
    }
    public void finishAuction(){
        this.timeFinishing = LocalDateTime.now();
        status = Status.FINISHED;
    }

    public void calcelAuction(){
        status = Status.CANCELED;
    }
}
