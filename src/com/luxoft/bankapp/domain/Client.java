package com.luxoft.bankapp.domain;

import java.time.LocalDate;
import java.util.*;

public class Client {
	
	private final String name;
	private final Gender gender;
	private final String city;
	private final Set<Account> accounts = new LinkedHashSet<>();

	public Client(String name, Gender gender, String city) {
		this.name = name;
		this.gender = gender;
		this.city = city;
	}
	
	public void addAccount(final Account account) {
		accounts.add(account);
	}
	
	public String getName() {
		return name;
	}
	
	public Gender getGender() {
		return gender;
	}

	public String getCity() {
		return city;
	}
	
	public Set<Account> getAccounts() {
		return Collections.unmodifiableSet(accounts);
	}
	
	public String getClientGreeting() {
		if (gender != null) {
			return gender.getGreeting() + " " + name;
		} else {
			return name;
		}
	}
	
	@Override
	public String toString() {
		return getClientGreeting();
	}

}
