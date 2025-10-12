package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
        super("The requested item doesn't exist");
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
