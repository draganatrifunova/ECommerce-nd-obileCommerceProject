package mk.ukim.finki.emt_2025.auctionApplication.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_CUSTOMER,
    ROLE_OWNER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
