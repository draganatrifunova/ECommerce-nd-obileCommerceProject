package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class AuctionNotFoundException extends RuntimeException{
    public AuctionNotFoundException() {
        super("The requested auction doesn't exist");
    }

    public AuctionNotFoundException(String message) {
        super(message);
    }
}
