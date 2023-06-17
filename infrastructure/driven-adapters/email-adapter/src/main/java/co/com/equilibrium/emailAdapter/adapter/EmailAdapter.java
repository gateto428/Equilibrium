package co.com.equilibrium.emailAdapter.adapter;

import co.com.equilibrium.emailAdapter.config.EmailProperties;
import co.com.equilibrium.model.commons.constnces.MessageConstances;
import co.com.equilibrium.model.person.gateways.EmailGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Repository
@RequiredArgsConstructor
public class EmailAdapter implements EmailGateway {
    private final EmailProperties emailProperties;


    private Mono<Boolean> sendMultiMessages(String emailRecipient, String subject, String message){
        try {
            Message msg = new MimeMessage(sessionConnection());
            msg.setFrom(new InternetAddress(emailProperties.getEmail()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
            return Mono.just(true);
        } catch (MessagingException e) {
            return Mono.just(false);
        }
    }

    @Override
    public Mono<Boolean> sendMessageWelcome(String name, String messageConstances, String emailRecipient, String subject){
        return sendMultiMessages(emailRecipient, subject, String.format(messageConstances, name));
    }

    @Override
    public Mono<Boolean> sendMessageNotificationActivated(String name,String id, String email) {
        return sendMultiMessages(emailProperties.getEmail(),
                MessageConstances.SUBJECT_NEW_USER.toString(),
                String.format(MessageConstances.ACTIVE_USER, name, id, email));
    }


    public Session sessionConnection(){
        Properties props = new Properties();
        props.put("mail.smtp.host", emailProperties.getHost());
        props.put("mail.smtp.port", emailProperties.getPort());
        props.put("mail.smtp.auth", emailProperties.getAuth());
        props.put("mail.smtp.starttls.enable", emailProperties.getStarttls());
        return Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return  new PasswordAuthentication(emailProperties.getEmail(),emailProperties.getPassword());
            }
        });
    }
}
