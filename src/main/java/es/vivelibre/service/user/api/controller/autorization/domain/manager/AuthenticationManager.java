package es.vivelibre.service.user.api.controller.autorization.domain.manager;

import es.vivelibre.service.user.api.controller.autorization.domain.entities.TokenResponse;
import es.vivelibre.service.user.api.controller.autorization.response.LoginResponse;

public interface AuthenticationManager {
    LoginResponse login(String user, String password) throws Exception;
}
