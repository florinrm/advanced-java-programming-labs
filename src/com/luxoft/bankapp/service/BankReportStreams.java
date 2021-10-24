package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;

import java.util.*;
import java.util.stream.Collectors;

public class BankReportStreams {
    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    public int getNumberOfAccounts(Bank bank) {
        return bank.getClients().stream().map(client -> client.getAccounts().size()).reduce(0, Integer::sum);
    }

    public SortedSet<Client> getClientsSorted(Bank bank) {
        return bank.getClients().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Client::getName))));
    }

    public double getTotalSumInAccounts(Bank bank) {
        return bank.getClients().stream().map(this::getClientAccountsTotalSum).reduce(0.0, Double::sum);
    }

    public SortedSet<Account> getAccountsSortedBySum(Bank bank) {
        SortedSet<Account> sortedAccountsBySum = new TreeSet<>(Comparator.comparingDouble(Account::getBalance));
        sortedAccountsBySum.addAll(
                bank.getClients().stream()
                .map(Client::getAccounts)
                .flatMap(Set::stream)
                .collect(Collectors.toList())
        );
        return sortedAccountsBySum;
    }

    public double getBankCreditSum(Bank bank) {
        return (-1) * bank.getClients().stream()
                .map(Client::getAccounts)
                .map(ArrayList::new)
                .flatMap(List::stream)
                .filter(account -> account instanceof CheckingAccount)
                .map(Account::getBalance)
                .filter(balance -> Double.compare(balance, 0) < 0)
                .reduce(0.0, Double::sum);
    }

    public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {
        Map<Client, Collection<Account>> bankCustomerAccounts = new HashMap<>();
        bank.getClients().forEach(client -> bankCustomerAccounts.put(client, client.getAccounts()));
        return bankCustomerAccounts;
    }

    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        return new TreeMap<>(bank.getClients().stream().collect(Collectors.groupingBy(Client::getCity)));
    }

    public Map<String, List<Client>> getClientsByBirthdayMonth(Bank bank) {
        return new TreeMap<>(
                bank.getClients().stream()
                        .collect(Collectors.groupingBy(client -> client.getBirthday().getMonth().name())));
    }

    private double getClientAccountsTotalSum(Client client) {
        return client.getAccounts().stream().map(Account::getBalance).reduce(0.0, Double::sum);
    }
}
