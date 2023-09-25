package com.dev.dao;

import com.dev.model.DashboardBankAccount;

public class PhoneBillPaymentTransactionImp implements DashboardTransactionDao {
	private String recipient;
	private String phoneNumber;
	private double amount;

	public PhoneBillPaymentTransactionImp(String recipient, String phoneNumber, double amount) {
		this.recipient = recipient;
		this.phoneNumber = phoneNumber;
		this.amount = amount;
	}

	@Override
	public void apply(DashboardBankAccount account) {
		account.withdraw(amount);
	}
}
