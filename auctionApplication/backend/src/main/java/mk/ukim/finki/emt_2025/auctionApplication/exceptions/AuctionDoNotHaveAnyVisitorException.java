package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class AuctionDoNotHaveAnyVisitorException extends RuntimeException{
    public AuctionDoNotHaveAnyVisitorException() {
        super("Auction do not have any visitors!");
    }

    public AuctionDoNotHaveAnyVisitorException(String message) {
        super(message);
    }
}
