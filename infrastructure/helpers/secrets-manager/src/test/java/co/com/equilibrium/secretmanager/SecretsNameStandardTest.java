package co.com.equilibrium.secretmanager;

import co.com.equilibrium.secretsmanager.SecretsNameStandard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import static org.assertj.core.api.Assertions.assertThat;

class SecretsNameStandardTest {
    @InjectMocks
    private SecretsNameStandard secretsNameStandard;
    private static  final String secretRds = "RDS";
    private static final  String secretEmail = "EMAIL";

    @BeforeEach
    void init () throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        final Field rds = SecretsNameStandard.class.getDeclaredField("secretRds");
        rds.setAccessible(true);
        rds.set(secretsNameStandard, secretRds);

        final Field email = SecretsNameStandard.class.getDeclaredField("secretEmail");
        email.setAccessible(true);
        email.set(secretsNameStandard, secretEmail);
    }

    @Test
    void getSecretRdsTest(){
        assertThat(secretsNameStandard.getSecretRds()).isNotNull();
    }

    @Test
    void getSecretEmailTest(){
        assertThat(secretsNameStandard.getSecretEmail()).isNotNull();
    }
}
