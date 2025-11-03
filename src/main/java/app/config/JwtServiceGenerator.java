package app.config;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.auth.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtServiceGenerator {

   
    public Map<String, Object> gerarPayload(Usuario usuario) {
    	
    	// AQUI VOCE PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
        Map<String, Object> payloadData = new HashMap<>();
        payloadData.put("username", usuario.getUsername());
        payloadData.put("id", usuario.getId().toString());
        payloadData.put("role", usuario.getRole());
        payloadData.put("outracoisa", "teste");
        
        return payloadData;
    }

    public String generateToken(Usuario usuario) {
        Map<String, Object> payloadData = this.gerarPayload(usuario);

        return Jwts.builder()
                .claims(payloadData) // ✅ Novo método sem depreciacao
                .subject(usuario.getUsername())
                .issuedAt(Date.from(Instant.now())) // ✅ Forma atualizada
                .expiration(Date.from(Instant.now().plusSeconds(3600 * JwtConfig.HORAS_EXPIRACAO_TOKEN)))
                .signWith(getSigningKey(), JwtConfig.ALGORITIMO_ASSINATURA) // ✅ Usando enum moderno
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // ✅ Novo método
                .build()
                .parseSignedClaims(token) // ✅ Novo método
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtConfig.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // ✅ Método adicional para extrair roles do token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ✅ Método adicional para extrair ID do usuário
    public Long extractUserId(String token) {
        String id = extractAllClaims(token).get("id", String.class);
        return Long.parseLong(id);
    }
}