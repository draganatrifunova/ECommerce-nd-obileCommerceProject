package mk.ukim.finki.emt_2025.auctionApplication.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name mustn't be blank")
    private String name;


    @Min(value = 1, message = "price must be greater than 0")
    private int price;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageDate;

    private boolean available;

    public Item(String name, int price, String imageName, String imageType, byte[] imageDate) {
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageDate = imageDate;
        this.available = true;
    }

    public Item() {
        this.available = true;
    }

    public void putItemInAuction(){
        this.available = false;
    }
    public void removeItemFromAuction(){
        this.available = true;
    }
}
