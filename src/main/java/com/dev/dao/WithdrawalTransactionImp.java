package com.dev.dao;

import com.dev.model.DashboardBankAccount;

public class WithdrawalTransactionImp implements DashboardTransactionDao {
	private double amount;

	public WithdrawalTransactionImp(double amount) {
		this.amount = amount;
	}

	@Override
	public void apply(DashboardBankAccount account) {
		account.withdraw(amount);
	}
}
