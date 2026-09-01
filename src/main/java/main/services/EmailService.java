package main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String email, String name) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Welcome to StoreMate");
        message.setText(
                "Hello " + name + ",\n\n" +
                "Welcome to StoreMate!\n\n" +
                "Your account has been successfully created.\n\n" +
                "You can now use StoreMate to manage your stores, inventory, sales, revenue and profit.\n\n" +
                "Thank you for using StoreMate.\n\n" +
                "Regards,\n" +
                "StoreMate"
        );

        mailSender.send(message);
    }

    public void sendDeletionEmail(String email, String name) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("StoreMate Account Deleted");
        message.setText(
                "Hello " + name + ",\n\n" +
                "Your StoreMate account has been successfully deleted.\n\n" +
                "All associated account data has been removed according to the account deletion process.\n\n" +
                "If you did not request this deletion, please contact us.\n\n" +
                "Regards,\n" +
                "StoreMate"
        );

        mailSender.send(message);
    }
}
