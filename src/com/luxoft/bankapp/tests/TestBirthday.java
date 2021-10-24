package com.luxoft.bankapp.tests;

import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.domain.Gender;
import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.service.BankReportStreams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestBirthday {
    private List<Client> clientList;
    private Bank bank;

    @Before
    public void setup() throws ClientExistsException {
        bank = new Bank();
        Client client1 = new Client(
                "Gigi Becali",
                Gender.MALE,
                "La stana de oi",
                LocalDate.of(1997, 11, 7));
        Client client2 = new Client(
                "Florin Mihalache",
                Gender.MALE,
                "Focsangeles", LocalDate.of(1997, 7, 7));
        Client client3 = new Client(
                "Centurista",
                Gender.FEMALE,
                "Pe centura", LocalDate.of(1969, 12, 2));
        Client client4 = new Client(
                "Alta panarama",
                Gender.MALE,
                "Panarama city",
                LocalDate.of(2001, 11, 9));
        Client client5 = new Client(
                "Caragiale",
                Gender.MALE,
                "Mitica",
                LocalDate.of(1852, 2, 13));
        Client client6 = new Client(
                "Cioran",
                Gender.MALE,
                "In depresie",
                LocalDate.of(1911, 4, 8));
        Client client7 = new Client(
                "Panarama din aia mare",
                Gender.MALE,
                "La stana de oi",
                LocalDate.of(1999, 12, 31));
        Client client8 = new Client(
                "DACUL LIBER",
                Gender.MALE,
                "COVRIG 19",
                LocalDate.of(1969, 5, 7));
        Client client9 = new Client(
                "Ceva copil de pe strada",
                Gender.MALE,
                "De unde sa stiu",
                LocalDate.of(2010, 11, 7));
        Client client10 = new Client(
                "Bucatareasa preferata",
                Gender.FEMALE,
                "La bucatarie",
                LocalDate.of(1998, 12, 4));

        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);
        bank.addClient(client4);
        bank.addClient(client5);
        bank.addClient(client6);
        bank.addClient(client7);
        bank.addClient(client8);
        bank.addClient(client9);
        bank.addClient(client10);
    }

    @Test
    public void testCheckNextMonthBirthdays() {
        LocalDate today = LocalDate.of(2021, 10, 24);
        var clients = bank.getClients().stream().filter(client -> {
            LocalDate clientBirthday = client.getBirthday();
            LocalDate nextMonth = today.plusDays(30);
            LocalDate birthdayNowDate = LocalDate.of(
                    today.getYear(),
                    clientBirthday.getMonthValue(),
                    clientBirthday.getDayOfMonth()
            );
            return birthdayNowDate.isEqual(today)
                    || (birthdayNowDate.isAfter(today) && birthdayNowDate.isBefore(nextMonth))
                    || birthdayNowDate.isEqual(nextMonth);
        } ).collect(Collectors.toList());
        System.out.println(clients);
        Assert.assertTrue(clients.contains(clientList.get(0)));
        Assert.assertTrue(clients.contains(clientList.get(3)));
        Assert.assertTrue(clients.contains(clientList.get(8)));
    }

    @Test
    public void testGetClientsByBirthdayMonth() {
        BankReportStreams bankReportStreams = new BankReportStreams();
        var clients = bankReportStreams.getClientsByBirthdayMonth(bank);
        System.out.println(clients);
        Assert.assertEquals("Cioran", clients.get("APRIL").get(0).getName());
    }
}
