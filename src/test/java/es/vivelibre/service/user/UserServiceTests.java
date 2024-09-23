package es.vivelibre.service.user;

import es.vivelibre.service.user.api.controller.autorization.aplication.LoginService;
import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginInput;
import es.vivelibre.service.user.api.controller.autorization.aplication.values.LoginOutput;
import es.vivelibre.service.user.api.controller.autorization.domain.manager.AuthenticationManager;
import es.vivelibre.service.user.api.controller.autorization.response.LoginResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceTests {

    @Test
    void shouldReturnLoginResult() throws Exception {
        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        doReturn(new LoginResponse("accesToken", new Date())).when(authenticationManager).login(anyString(), any());
        LoginService loginService = new LoginService(authenticationManager);
        LoginOutput result = loginService.execute(new LoginInput("test", "test"));
        Assertions.assertNotNull(result.getAccessToken());
        Assertions.assertNotNull(result.getCreationDate());
        verify(authenticationManager).login(anyString(), any());
    }
    @Test
    void shouldReturnExceptionWhenIncomplete() throws Exception {
        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        doReturn(new LoginResponse("accesToken", new Date())).when(authenticationManager).login(anyString(), any());
        LoginService loginService = new LoginService(authenticationManager);
        Exception exception = Assertions.assertThrows(Exception.class, ()-> {
            loginService.execute(new LoginInput(null, "test"));
        }
        );
        assert (exception.getMessage().equals("user is marked non-null but is null"));
    }

}
