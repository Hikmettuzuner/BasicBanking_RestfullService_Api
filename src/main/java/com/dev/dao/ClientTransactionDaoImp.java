package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dev.model.BankAccount;
import com.dev.model.transactions.DepositTransaction;
import com.dev.model.transactions.Transaction;
import com.dev.model.transactions.TransactionTypes;
import com.dev.model.transactions.WithdrawlTransaction;
import com.google.gson.Gson;

public class ClientTransactionDaoImp implements ClientTransactionDao {
	private static final String FIND_ACCOUNTNUMBER_STATEMENT = "SELECT * FROM AccountResult where accountNumber=?;";
	private static final String FIND_CURRENT_ACCOUNT_DATA_HEADER_STATEMENT = "SELECT * FROM AccountHeader where accountNumber=?;";

	private static final String INSERT_ACCOUNT_HEADER_STATEMENT = "insert into AccountHeader(accountName,accountNumber,balance,date) values(?,?,?,?);";
	private static final String INSERT_ACCOUNT_RESULT_STATEMENT = "insert into AccountResult(accountName,accountNumber,amount,date,type,approvalCode) values(?,?,?,?,?,?);";

	private static final String EXIST_ACCOUNT_RESULT_STATEMENT = "insert into AccountResult(accountName,accountNumber,amount,date,type,approvalCode) values(?,?,?,?,?,?);";
	private static final String EXIST_UPDATE_ACCOUNT_HEADER_STATEMENT = "update AccountHeader set balance = balance+?,date=? where accountNumber=?";

	private static final String RESULT_STATEMENT = "select TOP 1  * from AccountResult where accountNumber=? order by date desc";

	private static final String WITHDRAWMONEY_ACCOUNT_HEADER_STATEMENT = "update AccountHeader set balance = balance-?,date=? where accountNumber=?";
	private static final String WITHDRAWMONEY_ACCOUNT_RESULT_STATEMENT = "insert into AccountResult(accountName,accountNumber,amount,date,type,approvalCode) values(?,?,?,?,?,?);";

	public static Connection getConnection() {
		String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Eteration;user=sa;password=admin;encrypt=true;trustServerCertificate=true";
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			return DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			System.out.print("Unfortunatelly : " + e.getMessage());
		}
		return null;
	}

	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.print("Unfortunatelly : " + e.getMessage());
		}

	}

	@Override
	public String depositMoney(DepositTransaction depositTransaction, int accountNumber) {
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		Gson gson = new Gson();
		String responseData = null;
		BankAccount account = findAccount(accountNumber);
		Connection connection = getConnection();
		if (account != null) {
			//// THİS ACCOUNT NUMBER EXIST AT SYSTEM, we will update

			try {

				PreparedStatement preparedStatement1 = connection
						.prepareStatement(EXIST_UPDATE_ACCOUNT_HEADER_STATEMENT);
				preparedStatement1.setFloat(1, depositTransaction.getAmount());
				preparedStatement1.setTimestamp(2, currentTimestamp);
				preparedStatement1.setInt(3, account.getAccountNumber());
				preparedStatement1.executeUpdate();

				PreparedStatement preparedStatement2 = connection.prepareStatement(EXIST_ACCOUNT_RESULT_STATEMENT);
				preparedStatement2.setString(1, account.getAccountName());
				preparedStatement2.setInt(2, account.getAccountNumber());
				preparedStatement2.setFloat(3, depositTransaction.getAmount());
				preparedStatement2.setTimestamp(4, currentTimestamp);
				preparedStatement2.setString(5, depositTransaction.getTransactionType().toString());
				preparedStatement2.setString(6, depositTransaction.getApprovalCode());
				preparedStatement2.executeUpdate();

				responseData = gson.toJson(depositTransaction);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.print("Unfortunatelly : " + e.getMessage());
				close(connection);
				return "Unfortunatelly exception exist : " + e.getMessage();
			}
			close(connection);
			return responseData;
		} else {
			return "FİRST POST OPERATION FOR THİS ACCOUNT NUMBER, new customer";
		}
		//// FİRST POST OPERATION FOR THİS ACCOUNT NUMBER, new customer

	}

	@Override
	public String withdrawlMoney(WithdrawlTransaction withdrawlTransaction, int accountNumber) {
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		Gson gson = new Gson();
		String responseData = null;
		BankAccount account = findAccount(accountNumber);
		Connection connection = getConnection();

		try {

			PreparedStatement preparedStatement1 = connection.prepareStatement(WITHDRAWMONEY_ACCOUNT_HEADER_STATEMENT);
			preparedStatement1.setFloat(1, withdrawlTransaction.getAmount());
			preparedStatement1.setTimestamp(2, currentTimestamp);
			preparedStatement1.setInt(3, accountNumber);
			preparedStatement1.executeUpdate();

			PreparedStatement preparedStatement2 = connection.prepareStatement(WITHDRAWMONEY_ACCOUNT_RESULT_STATEMENT);
			preparedStatement2.setString(1, account.getAccountName());
			preparedStatement2.setInt(2, account.getAccountNumber());
			preparedStatement2.setFloat(3, withdrawlTransaction.getAmount());
			preparedStatement2.setTimestamp(4, currentTimestamp);
			preparedStatement2.setString(5, withdrawlTransaction.getTransactionType().toString());
			preparedStatement2.setString(6, withdrawlTransaction.getApprovalCode());
			preparedStatement2.executeUpdate();

			responseData = gson.toJson(withdrawlTransaction);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("Unfortunatelly : " + e.getMessage());
			close(connection);
			return "Unfortunatelly exception exist : " + e.getMessage();
		}
		close(connection);
		return responseData;
	}

	@Override
	public BankAccount findAccount(int accountNumber) {
		Connection connection = getConnection();
		int findAccountNumber = 0;

		BankAccount bankAccount = new BankAccount();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(FIND_CURRENT_ACCOUNT_DATA_HEADER_STATEMENT);
			preparedStatement.setInt(1, accountNumber);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bankAccount.setAccountName(rs.getString("accountName"));
				bankAccount.setAccountNumber(rs.getInt("accountNumber"));
				bankAccount.setBalance(rs.getFloat("balance"));
				bankAccount.setDate(rs.getDate("date").toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Unfortunatelly : " + e.getMessage());
			close(connection);
			return bankAccount;
		}

		bankAccount.setList(findCurrentAccountTransactions(accountNumber));

		close(connection);
		return bankAccount;

	}

	@Override
	public ArrayList<Transaction> findCurrentAccountTransactions(int accountNumber) {
		Connection connection = getConnection();
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACCOUNTNUMBER_STATEMENT);
			preparedStatement.setInt(1, accountNumber);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DepositTransaction depositTransaction = new DepositTransaction();
				WithdrawlTransaction withdrawlTransaction = new WithdrawlTransaction();
				Transaction transaction = new Transaction();
				TransactionTypes type = TransactionTypes.valueOf(rs.getString("type"));
				if (type.equals(TransactionTypes.DepositTransaction)) {
					depositTransaction.setTransactionType(TransactionTypes.valueOf(rs.getString("type")));
					depositTransaction.setAmount(rs.getFloat("amount"));
					depositTransaction.setApprovalCode(rs.getString("approvalCode"));
					depositTransaction.setDate(rs.getDate("date").toString());
					transactionList.add(depositTransaction);
				} else if (type.equals(TransactionTypes.WithdrawlTransaction)) {
					withdrawlTransaction.setTransactionType(TransactionTypes.valueOf(rs.getString("type")));
					withdrawlTransaction.setAmount(rs.getFloat("amount"));
					withdrawlTransaction.setApprovalCode(rs.getString("approvalCode"));
					withdrawlTransaction.setDate(rs.getDate("date").toString());
					transactionList.add(withdrawlTransaction);
				} else {
					transaction.setTransactionType(TransactionTypes.valueOf(rs.getString("type")));
					transaction.setAmount(rs.getFloat("amount"));
					transaction.setApprovalCode(rs.getString("approvalCode"));
					transaction.setDate(rs.getDate("date").toString());
					transactionList.add(transaction);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Unfortunatelly : " + e.getMessage());
			close(connection);
		}
		close(connection);
		return transactionList;

	}

}
