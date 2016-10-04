package com.example.model;

import java.time.LocalDate;

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

}
