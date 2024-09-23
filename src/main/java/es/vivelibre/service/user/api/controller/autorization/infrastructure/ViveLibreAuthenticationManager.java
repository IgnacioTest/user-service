package es.vivelibre.service.user.api.controller.autorization.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.vivelibre.service.user.api.controller.autorization.domain.entities.TokenResponse;
import es.vivelibre.service.user.api.controller.autorization.domain.manager.AuthenticationManager;
import es.vivelibre.service.user.api.controller.autorization.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Component
public class ViveLibreAuthenticationManager implements AuthenticationManager {
    private final ObjectMapper objectMapper;
    private static final String PATH_CREATE = "http://localhost:8080/token";

    @Override
    public LoginResponse login(String user, String password) throws Exception {
        log.info("login - auth-vivelibre");
        LoginResponse result = new LoginResponse();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Date date = today.getTime();
        Map<String, String> params = new HashMap<>();
        params.put("username", user);
        params.put("password", password);
        String body = buildUrlEncodedForm(params);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PATH_CREATE))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse <String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        TokenResponse tokenResponse = objectMapper.readValue(response.body(), TokenResponse.class);
        result.setAccessToken(tokenResponse.getAccessToken());
        result.setDate(date);

        return result;
    }

    public static String buildUrlEncodedForm(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return result.toString();
    }
}
