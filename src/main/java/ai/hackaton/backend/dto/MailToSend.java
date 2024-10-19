package ai.hackaton.backend.dto;

import lombok.Data;

@Data
public class MailToSend {
    private String emailToSend;
    private String text;
}
