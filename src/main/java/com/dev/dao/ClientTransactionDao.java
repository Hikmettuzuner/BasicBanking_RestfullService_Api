package com.dev.dao;

import java.util.List;

import com.dev.model.BankAccount;
import com.dev.model.transactions.DepositTransaction;
import com.dev.model.transactions.Transaction;
import com.dev.model.transactions.WithdrawlTransaction;

public interface ClientTransactionDao {
	
	String depositMoney(DepositTransaction depositTransaction, int accountNumber);

	String withdrawlMoney(WithdrawlTransaction withdrawlTransaction, int accountNumber);

	List<Transaction> findCurrentAccountTransactions(int accountNumber);
	
	BankAccount findAccount(int accountNumber);
}
