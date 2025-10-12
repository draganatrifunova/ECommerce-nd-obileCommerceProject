package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class AuctionStatusIsNotProper extends RuntimeException {
    public AuctionStatusIsNotProper() {
        super("Auction status is not proper for this activity!");
    }

    public AuctionStatusIsNotProper(String message) {
        super(message);
    }
}
