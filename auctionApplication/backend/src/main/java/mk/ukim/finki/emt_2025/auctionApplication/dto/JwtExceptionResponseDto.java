package mk.ukim.finki.emt_2025.auctionApplication.dto;

import java.util.Date;

public record JwtExceptionResponseDto(
        Date timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public JwtExceptionResponseDto(int status, String error, String message, String path) {
        this(new Date(), status, error, message, path);
    }
}
