package es.vivelibre.service.user.api.controller.autorization.domain.mappers;

import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginOutput;
import es.vivelibre.service.user.api.controller.autorization.domain.entities.TokenResponse;
import es.vivelibre.service.user.api.controller.autorization.response.LoginResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorizationMapper {

    public static LoginOutput toLoginResponse(LoginResponse result) {
        return LoginOutput.of(result.getAccessToken(), result.getDate());
    }
}
