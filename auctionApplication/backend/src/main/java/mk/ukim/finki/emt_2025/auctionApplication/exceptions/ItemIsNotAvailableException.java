package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class ItemIsNotAvailableException extends RuntimeException{
    public ItemIsNotAvailableException() {
        super("This item is already chosen for another auction");
    }

    public ItemIsNotAvailableException(String message) {
        super(message);
    }
}
