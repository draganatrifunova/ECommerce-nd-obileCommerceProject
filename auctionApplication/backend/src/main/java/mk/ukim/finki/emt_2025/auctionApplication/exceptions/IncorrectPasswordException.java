package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(){
        super("The password is incorrect.");
    }
}
