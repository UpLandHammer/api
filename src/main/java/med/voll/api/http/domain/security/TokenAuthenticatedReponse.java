package med.voll.api.http.domain.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenAuthenticatedReponse {
    private String token;
}
