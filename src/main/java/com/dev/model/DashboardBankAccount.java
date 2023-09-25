package com.dev.model;

import com.dev.dao.DashboardTransactionDao;

public class DashboardBankAccount {
	private String accountHolder;
	private int accountNumber;
	private double balance;
	public double previousTransaction;

	public DashboardBankAccount(String accountHolder, int accountNumber) {
		super();
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
		this.balance = 0.0;
	}

	public DashboardBankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void post(DashboardTransactionDao dashboardTransactionDao) {
		dashboardTransactionDao.apply(this);
	}

	public void deposit(double amount) {
		if (amount != 0) {
			balance += amount;
			previousTransaction = amount;
		}

	}

	public void withdraw(double amount) {
		if (amount != 0) {
			balance -= amount;
			previousTransaction -= amount;
		}

	}

	public double getBalance() {
		return balance;
	}

	public String getPreviousTransaction() {
		if (previousTransaction > 0) {
			return "Deposited: " + previousTransaction;
		} else if (previousTransaction < 0) {
			return "Withdrawn: " + Math.abs(previousTransaction);
		} else {
			return "No transaction is occured";
		}
	}

}
