package com.luxoft.bankapp.tests;

import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.domain.Email;
import com.luxoft.bankapp.domain.Gender;
import com.luxoft.bankapp.exceptions.EmailException;
import com.luxoft.bankapp.service.EmailService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestEmailService {
    private static final int NO_EMAILS = 20;
    private Client client;
    private Client to;

    @Before
    public void setup() {
        client = new Client("Gigi Becali", Gender.MALE, "La stana de oi", LocalDate.of(1997, 7, 7));
        to = new Client("Bulake cel Lake", Gender.MALE, "Ferentari", LocalDate.of(1997, 7, 7));
    }

    @Test
    public void testSendMail() throws InterruptedException {

        EmailService emailService = new EmailService();
        for (int i = 0; i < NO_EMAILS; i++) {
            try {
                emailService.sendNotificationEmail(
                        new Email()
                                .setTitle("New client has been added")
                                .setBody("New client " + client + " has been added in system")
                                .setFrom(client)
                                .setTo(List.of(to))
                );
            } catch (EmailException e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }

        assertEquals(NO_EMAILS, emailService.getSentEmails());

    }
}
