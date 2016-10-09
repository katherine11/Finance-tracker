package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Budget {
	
	private int userId;
	private int expenseId;
	private String expense;
	private int repeatingId;
	private String repeating;
	private double amount;
	private LocalDate date;
	private String description;
	
	public Budget(int userId, int expenseId, String expense, int repeatingId, String repeating, double amount, LocalDate date, String description) {
		this.userId = userId;
		this.expenseId = expenseId;
		this.expense = expense;
		this.repeatingId = repeatingId;
		this.repeating = repeating;
		this.amount = amount;
		this.date = date;
		this.description = description;
	}

	public Budget() {}

	public int getUserId() {
		return userId;
	}

	public int getExpenseId() {
		return expenseId;
	}

	public String getExpense() {
		return expense;
	}

	public String getRepeating() {
		return repeating;
	}

	public int getRepeatingId() {
		return repeatingId;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public void setRepeatingId(int repeatingId) {
		this.repeatingId = repeatingId;
	}

	public void setRepeating(String repeating) {
		this.repeating = repeating;
	}

	public void setDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate parsedDate = LocalDate.parse(date, formatter);
		this.date = parsedDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + expenseId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Budget other = (Budget) obj;
		if (expenseId != other.expenseId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Budget [expense=" + expense + ", repeating=" + repeating + ", amount=" + amount + ", date=" + date + ", description="
				+ description + "]";
	}
	
	

}
