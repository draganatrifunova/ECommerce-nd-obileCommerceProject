package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class UsernameMustBeUniqueException extends RuntimeException{
    public UsernameMustBeUniqueException() {
        super("This username already exist");
    }

    public UsernameMustBeUniqueException(String message) {
        super(message);
    }
}
