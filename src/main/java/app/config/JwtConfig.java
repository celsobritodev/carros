package app.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;


public class JwtConfig {
	
    // Parâmetros para geração do token ( uma chave secreta essencial que só o seu back saiba )
    public static final String SECRET_KEY = "UMACHAVESECRETADASUAAPIAQUIUMACHAVESECRETADASUAAPIAQUIUMACHAVESECRETADASUAAPIAQUIUMACHAVESECRETADASUAAPIAQUI";
    public static final MacAlgorithm  ALGORITIMO_ASSINATURA = Jwts.SIG.HS256;
    public static final int HORAS_EXPIRACAO_TOKEN = 1;


}
