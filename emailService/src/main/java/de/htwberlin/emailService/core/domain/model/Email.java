package de.htwberlin.emailService.core.domain.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public class Email {
    @Getter
    private String receiver;
    @Getter
    private String subject;
    @Getter
    private String content;
}
