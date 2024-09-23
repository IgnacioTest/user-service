package es.vivelibre.service.user.api.controller.autorization.mappers;

import es.vivelibre.service.user.api.controller.autorization.request.LoginRequest;
import es.vivelibre.service.user.api.controller.autorization.response.LoginResponse;
import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginInput;
import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginOutput;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorizationControllerMapper {

    public static LoginInput buildInput(LoginRequest request) {
        return new LoginInput(request.getUsername(), request.getPassword());
    }

    public static LoginResponse toResponse(LoginOutput result) {
        return new LoginResponse(result.getAccessToken(), result.getCreationDate());
    }
}
