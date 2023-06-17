package co.com.equilibrium.jwtAdapter.adapter;

import co.com.equilibrium.jwtAdapter.config.JWTProperties;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.person.gateways.JWTGateway;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Date;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.TOKEN_INVALID;

@Repository
@RequiredArgsConstructor
public class JTWAdapter implements JWTGateway {
    private StrongTextEncryptor encryptor;
    private final JWTProperties properties;
   @Override
    public String getToken(Person person){
        long time = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(person.getEmailPerson())
                .setIssuedAt(new Date(time))
                .setExpiration(new Date(time + 9000000))
                .claim("idPerson", person.getIdPerson())
                .claim("namePerson", person.getNamePerson())
                .claim("lastNamePerson", person.getLastNamePerson())
                .claim("phonePerson", person.getPhonePerson())
                .claim("rolType", person.getRolType())
                .signWith(SignatureAlgorithm.HS256, properties.getCode())
                .compact();
    }

    @Override
    public Mono<String> getKeyEncrypt(String pass) {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(properties.getCode());
        return Mono.fromSupplier(() -> encryptor.encrypt(pass));
    }

    @Override
    public Mono<String> getKeyDecrypt(String pass) {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(properties.getCode());
        return Mono.fromSupplier(() -> encryptor.decrypt(pass));
    }

    @Override
    public boolean verifyToken(String token) {
        return Mono.fromCallable(() -> {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(properties.getCode())
                        .parseClaimsJws(token)
                        .getBody();
                return true;
            } catch (SignatureException e) {
                throw new TechnicalException(TOKEN_INVALID);
            }
        }).block();
    }
}
