package es.vivelibre.service.user.api.controller.autorization.aplication.values;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginInput {

    @NonNull
    String user;
    @NonNull
    String password;
}
