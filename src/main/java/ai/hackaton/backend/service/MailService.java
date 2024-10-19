package ai.hackaton.backend.service;

import ai.hackaton.backend.dto.MailToSend;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

//    public void sendMail(Mail mail) {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject("Запрос тех поддержки от " + mail.getSubject());
//        message.setText(mail.getText());
//        message.setTo("azamatbakyt02@gmail.com");
//        message.setReplyTo(mail.getEmail());
//        mailSender.send(message);
//
//        System.out.println("Mail sent successfully...");
//    }

    public void sendMimeMail(MailToSend mail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(mail.getEmailToSend());
            helper.setSubject("Рекомендации чата ассистента");
            helper.setText(mail.getText());

            mailSender.send(message);
            System.out.println("Mail sent successfully...");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
