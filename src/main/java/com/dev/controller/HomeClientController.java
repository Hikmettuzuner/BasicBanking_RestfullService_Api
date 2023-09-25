package com.dev.controller;

import java.util.Random;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.dao.ClientTransactionDao;
import com.dev.dao.ClientTransactionDaoImp;
import com.dev.model.BankAccount;
import com.dev.model.transactions.DepositTransaction;
import com.dev.model.transactions.Transaction;
import com.dev.model.transactions.TransactionTypes;
import com.dev.model.transactions.WithdrawlTransaction;
import com.google.gson.Gson;

@Controller
public class HomeClientController {

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/accountDeposit", method = RequestMethod.POST)
	@ResponseBody
	public String accountDeposit(@RequestParam("accountName") String accountName,
			@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") float amount) {

		String value = generateRandomApprovalCode();

		DepositTransaction depositTransaction = new DepositTransaction();
		depositTransaction.setAmount(amount);
		depositTransaction.setApprovalCode(value);
		depositTransaction.setTransactionType(TransactionTypes.DepositTransaction);

		ClientTransactionDao clientTransactionDao = new ClientTransactionDaoImp();
		BankAccount bankAccount = new BankAccount();
		if (value.length() > 0) {
			bankAccount.setAccountName(accountName);
			bankAccount.setAccountNumber(accountNumber);
			bankAccount.addTransaction(depositTransaction);
			bankAccount.setBalance(amount);
			return clientTransactionDao.depositMoney(depositTransaction, accountNumber);
		} else {
			return "It was caused problem";
		}

	}

	@RequestMapping(value = "/accountWithdrawMoney", method = RequestMethod.POST)
	@ResponseBody
	public String accountWithdrawMoney(@RequestParam("accountName") String accountName,
			@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") float amount) {
		ClientTransactionDao clientTransactionDao = new ClientTransactionDaoImp();
		
		String value = generateRandomApprovalCode();
		
		WithdrawlTransaction withdrawlTransaction = new WithdrawlTransaction();
		withdrawlTransaction.setAmount(amount);
		withdrawlTransaction.setApprovalCode(value);
		withdrawlTransaction.setTransactionType(TransactionTypes.WithdrawlTransaction);
		
		BankAccount bankAccount = new BankAccount();
		if (value.length() > 0) {
			bankAccount = clientTransactionDao.findAccount(accountNumber);
			bankAccount.setAccountName(accountName);
			bankAccount.setAccountNumber(accountNumber);
			bankAccount.addTransaction(withdrawlTransaction);
			bankAccount.setBalance(amount);
			return clientTransactionDao.withdrawlMoney(withdrawlTransaction, accountNumber);
		} else {
			return "It was caused problem";
		}

	}

	@RequestMapping(value = "/getCurrentAccountData", method = RequestMethod.GET)
	@ResponseBody
	public String getCurrentAccountData(@RequestParam("accountNumber") int accountNumber) {
		ClientTransactionDao clientTransactionDao = new ClientTransactionDaoImp();
		BankAccount bankAccount = new BankAccount();
		bankAccount = clientTransactionDao.findAccount(accountNumber);
		if (bankAccount != null) {
			Gson gson = new Gson();
			return gson.toJson(bankAccount);
		} else {
			return "No Exist Current Account Data! PLEASE firstly you must do Deposit Transaction POST operation";
		}

	}

	public static String generateRandomApprovalCode() {
		Random random = new Random();
		return String.format("%04d", random.nextInt(10000));
	}

}
