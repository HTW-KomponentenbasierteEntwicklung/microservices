package de.htwberlin.emailService.core.model;

import lombok.Getter;

import java.util.UUID;

public class Email {
    @Getter
    private String receiver;
    @Getter
    private String subject;
    @Getter
    private String content;

    public Email(String receiver, String subject, String content) {
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
    }
}
