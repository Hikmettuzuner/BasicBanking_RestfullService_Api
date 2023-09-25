package com.dev.dao;

import com.dev.model.DashboardBankAccount;

public class DepositTransactionImp implements DashboardTransactionDao {
	private double amount;

	public DepositTransactionImp(double amount) {
		this.amount = amount;
	}

	@Override
	public void apply(DashboardBankAccount account) {
		account.deposit(amount);
	}
}
