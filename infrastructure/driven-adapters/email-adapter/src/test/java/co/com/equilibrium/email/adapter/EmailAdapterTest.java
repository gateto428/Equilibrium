package co.com.equilibrium.email.adapter;

import co.com.equilibrium.emailAdapter.adapter.EmailAdapter;
import co.com.equilibrium.emailAdapter.config.EmailProperties;
import co.com.equilibrium.model.commons.constnces.MessageConstances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EmailAdapterTest {
    @InjectMocks
    private EmailAdapter emailAdapter;
    private EmailProperties emailProperties = new EmailProperties();

    @BeforeEach
    public void init() throws NoSuchFieldException, IllegalAccessException {
        emailProperties.setHost("smtp.gmail.com");
        emailProperties.setPort("2");
        emailProperties.setAuth("true");
        emailProperties.setStarttls("true");
        emailProperties.setEmail("test@email.com");
        emailProperties.setPassword("abves123");

        final Field properties = EmailAdapter.class.getDeclaredField("emailProperties");
        properties.setAccessible(true);
        properties.set(emailAdapter, emailProperties);
    }

    @Test
    void sendMultiMessagesTest() {
        assertThat(emailAdapter
                .sendMessageWelcome("nicolas",
                        MessageConstances.WELCOME_MESSAGE_INACTIVE,
                        "test@gmail.com",
                        "subject")).isNotNull();
    }

    @Test
    void sendMessageNotificationActivatedTest() {
        assertThat(emailAdapter
                .sendMessageNotificationActivated("nicolas",
                        "123", "test@test.com")).isNotNull();
    }
}
