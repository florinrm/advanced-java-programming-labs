package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;

import java.util.*;
import java.util.stream.Collectors;

public class BankReport {
    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    public int getNumberOfAccounts(Bank bank) {
        var totalAccounts = 0;
        for (var client : bank.getClients()) {
            totalAccounts += client.getAccounts().size();
        }
        return totalAccounts;
    }

    public SortedSet<Client> getClientsSorted(Bank bank) {
        SortedSet<Client> sortedClients = new TreeSet<>(Comparator.comparing(Client::getName));
        sortedClients.addAll(bank.getClients());
        return sortedClients;
    }

    public double getTotalSumInAccounts(Bank bank) {
        double totalSum = 0;
        for (var client : bank.getClients()) {
            for (var account : client.getAccounts()) {
                totalSum += account.getBalance();
            }
        }
        return totalSum;
    }

    public SortedSet<Account> getAccountsSortedBySum(Bank bank) {
        SortedSet<Account> sortedAccountsBySum = new TreeSet<>(Comparator.comparingDouble(Account::getBalance));
        List<Account> accounts = new ArrayList<>();
        bank.getClients().forEach(client -> accounts.addAll(client.getAccounts()));
        sortedAccountsBySum.addAll(accounts);
        return sortedAccountsBySum;
    }

    public double getBankCreditSum(Bank bank) {
        List<Account> accounts = new ArrayList<>();
        bank.getClients().forEach(client -> accounts.addAll(client.getAccounts()));
        double sum = 0;
        for (var account: accounts) {
            if (account instanceof CheckingAccount) {
                if (Double.compare(account.getBalance(), 0) < 0) {
                    sum -= account.getBalance();
                }
            }
        }
        return sum;
    }

    public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {
        Map<Client, Collection<Account>> bankCustomerAccounts = new HashMap<>();
        bank.getClients().forEach(client -> bankCustomerAccounts.put(client, client.getAccounts()));
        return bankCustomerAccounts;
    }

    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        SortedSet<String> cities = new TreeSet<>();
        Map<String, List<Client>> clientsByCities = new TreeMap<>();
        bank.getClients().forEach(client -> cities.add(client.getCity()));
        for (var city: cities) {
            List<Client> clients = new ArrayList<>();
            for (var client: bank.getClients()) {
                if (client.getCity().equals(city)) {
                    clients.add(client);
                }
            }
            clientsByCities.put(city, clients);
        }
        return clientsByCities;
    }
}
