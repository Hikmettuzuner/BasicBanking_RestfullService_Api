package com.dev.dao;

import com.dev.model.DashboardBankAccount;

public interface DashboardTransactionDao {
	void apply(DashboardBankAccount account);
}
