package es.vivelibre.service.user.api.controller.autorization.aplication;

import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginInput;
import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginOutput;
import es.vivelibre.service.user.api.controller.autorization.domain.manager.AuthenticationManager;
import es.vivelibre.service.user.api.controller.autorization.domain.mappers.AuthorizationMapper;
import es.vivelibre.service.user.api.controller.autorization.response.LoginResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public LoginOutput execute(LoginInput input) throws Exception {
        log.info("login - application");
        try {
            LoginResponse loginResponse = authenticationManager.login(input.getUser(), input.getPassword());
            return AuthorizationMapper.toLoginResponse(loginResponse);
        } catch (Exception e) {
            log.error("Error in login", e);
            throw new Exception("Error in login", e);
        }

    }
}
