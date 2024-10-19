package ai.hackaton.backend.controller;

import ai.hackaton.backend.dto.MailToSend;
import ai.hackaton.backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailController {


    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody MailToSend mail) {
        mailService.sendMimeMail(mail);
        return ResponseEntity.ok("Mail sent successfully to " + mail.getEmailToSend());
    }
}
