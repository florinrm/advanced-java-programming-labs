package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Email;
import com.luxoft.bankapp.exceptions.EmailException;
import com.luxoft.bankapp.queue.Queue;

public class EmailService implements Runnable {
    private final Queue<Email> emailStorage = new Queue<>();
    private final Thread currentThread;
    private boolean isServiceClosed = false;
    private int sentEmails = 0;

    public EmailService() {
        currentThread = new Thread(this);
        currentThread.start();
    }

    public void sendNotificationEmail(Email email) throws EmailException {
        if (!isServiceClosed) {
            emailStorage.add(email);
            synchronized (emailStorage) {
                emailStorage.notify();
            }
        } else {
            throw new EmailException("Email service is closed, mails cannot be sent!");
        }
    }

    // consuming sent emails
    @Override
    public void run() {
        for (;;) {
            if (isServiceClosed) {
                return;
            }

            Email email = emailStorage.get();
            if (email != null) {
                consumeEmail(email);
            }
            synchronized (emailStorage) {
                try {
                    emailStorage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void close() {
        isServiceClosed = true;
        synchronized (emailStorage) {
            emailStorage.notify();
        }
        try {
            currentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getSentEmails() {
        return sentEmails;
    }

    private void consumeEmail(Email email) {
        ++sentEmails;
        System.out.println("Email successfully sent!\n" + email);
    }
}
