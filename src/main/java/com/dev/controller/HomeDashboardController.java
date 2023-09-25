package com.dev.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dev.dao.DepositTransactionImp;
import com.dev.dao.PhoneBillPaymentTransactionImp;
import com.dev.dao.WithdrawalTransactionImp;
import com.dev.model.DashboardBankAccount;

@Controller
public class HomeDashboardController {
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		return modelAndView;
	}

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ModelAndView transactions(@RequestParam("accountName") String accountName,
			@RequestParam("accountId") String accountId) {
		ModelAndView modelAndView = new ModelAndView("home");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int newAccountId = Integer.valueOf(accountId);
		DashboardBankAccount account = new DashboardBankAccount(accountName, newAccountId);
		account.post(new DepositTransactionImp(1000));
		System.out.println("DepositTransactionImp: " + account.getPreviousTransaction());
		account.post(new WithdrawalTransactionImp(200));
		System.out.println("WithdrawalTransactionImp: " + account.getPreviousTransaction());
		account.post(new PhoneBillPaymentTransactionImp("Vodafone", "5423345566", 96.50));
		System.out.println("PhoneBillPaymentTransactionImp: " + account.getPreviousTransaction());
		double expectedBalance = 703.50;
		System.out.println("Expected Balance: " + expectedBalance);

		if (Math.abs(account.getBalance() - expectedBalance) < 0.0001) {
			System.out.println("Test passed!");
		} else {
			System.out.println("Test failed!");
		}

		return modelAndView;
	}

}
