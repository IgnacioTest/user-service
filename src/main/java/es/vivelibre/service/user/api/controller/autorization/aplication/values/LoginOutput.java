package es.vivelibre.service.user.api.controller.autorization.aplication.values;

import lombok.Value;

import java.util.Date;

@Value(staticConstructor = "of")
public class LoginOutput {

    private String accessToken;
    private Date creationDate;
}
