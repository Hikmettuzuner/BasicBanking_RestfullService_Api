package com.dev.model;

import java.util.ArrayList;
import java.util.List;

import com.dev.model.transactions.DepositTransaction;
import com.dev.model.transactions.Transaction;

public class BankAccount {
	private String accountName;
	private int accountNumber;
	private float balance;
	private String createDate;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	public String getDate() {
		return createDate;
	}

	public void setDate(String createDate) {
		this.createDate = createDate;
	}

	public ArrayList<Transaction> getList() {
		return transactions;
	}

	public void setList(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float amount) {
		this.balance = amount;
	}


	public BankAccount(String accountName, int accountNumber, float amount) {
		super();
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.balance = amount;
	
	}
	
//	public void post(Transaction transaction) {
//		
//	}
//	
//	private String deposit(Transaction transaction) {
//		
//		
//		return "blabla";
//	}
	
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

}
