package com.dev.model.transactions;

import java.util.Date;

public class Transaction {
	public String date;
	public Float amount;
	public TransactionTypes transactionType; 
	public String approvalCode;
	
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	public TransactionTypes getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionTypes transactionType) {
		this.transactionType = transactionType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	} 
	
}


