package com.luxoft.bankapp.domain;

import java.time.LocalDate;
import java.util.*;

public class Client {
	
	private final String name;
	private final Gender gender;
	private final String city;
	private final LocalDate birthday;
	private final Set<Account> accounts = new LinkedHashSet<>();

	public Client(String name, Gender gender, String city, LocalDate birthday) {
		this.name = name;
		this.gender = gender;
		this.city = city;
		this.birthday = birthday;
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

	public LocalDate getBirthday() {
		return birthday;
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
