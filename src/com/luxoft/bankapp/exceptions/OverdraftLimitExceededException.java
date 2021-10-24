package com.luxoft.bankapp.exceptions;

public class OverdraftLimitExceededException extends NotEnoughFundsException {
	private final double overdraft;

	public OverdraftLimitExceededException(NotEnoughFundsException e, double overdraft) {
        super(e.getId(), e.getBalance(), e.getAmount(), e.getMessage());
        this.overdraft = overdraft;
    }
	
	public double getOverdraft() {
		return overdraft;
	}

}
