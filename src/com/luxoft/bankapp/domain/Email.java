package com.luxoft.bankapp.domain;

import java.util.List;

public class Email {
    private Client client;
    private Client from;
    private List<Client> to;
    private String title;
    private String body;

    public Email(Client client, Client from, List<Client> to, String title, String body) {
        this.client = client;
        this.from = from;
        this.to = to;
        this.title = title;
        this.body = body;
    }

    public Email() {

    }

    public Client getClient() {
        return client;
    }

    public Email setClient(Client client) {
        this.client = client;
        return this;
    }

    public Client getFrom() {
        return from;
    }

    public Email setFrom(Client from) {
        this.from = from;
        return this;
    }

    public List<Client> getTo() {
        return to;
    }

    public Email setTo(List<Client> to) {
        this.to = to;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Email setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Email setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return "Email{" +
                "client=" + client +
                ", from=" + from +
                ", to=" + to +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
