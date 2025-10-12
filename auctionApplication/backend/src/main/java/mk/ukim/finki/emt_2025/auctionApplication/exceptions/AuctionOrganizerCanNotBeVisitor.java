package mk.ukim.finki.emt_2025.auctionApplication.exceptions;

public class AuctionOrganizerCanNotBeVisitor extends RuntimeException{
    public AuctionOrganizerCanNotBeVisitor() {
        super("The auction organizer can not be visitor");
    }

    public AuctionOrganizerCanNotBeVisitor(String message) {
        super(message);
    }
}
