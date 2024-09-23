package es.vivelibre.service.user.api.controller.autorization;

import es.vivelibre.service.user.api.controller.autorization.aplication.LoginService;
import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginOutput;
import es.vivelibre.service.user.api.controller.autorization.mappers.AuthorizationControllerMapper;
import es.vivelibre.service.user.api.controller.autorization.request.LoginRequest;
import es.vivelibre.service.user.api.controller.autorization.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
@Validated
@RequiredArgsConstructor
public class AuthorizationController {

    private final LoginService loginService;

    @GetMapping("/get-token")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) throws Exception {
        log.info("login - controller");
        LoginOutput result = this.loginService.execute(AuthorizationControllerMapper.buildInput(request));
        return ResponseEntity.ok(AuthorizationControllerMapper.toResponse(result));
    }


}
