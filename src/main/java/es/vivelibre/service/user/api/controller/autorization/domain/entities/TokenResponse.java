package es.vivelibre.service.user.api.controller.autorization.domain.entities;

import lombok.Getter;

import java.util.Date;

@Getter
public class TokenResponse {

    private final String accessToken;

    public TokenResponse(String accessToken, Date creationDate) {
        this.accessToken = accessToken;
    }
}
