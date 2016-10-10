package com.example.model;

import static org.hamcrest.CoreMatchers.nullValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.model.exceptions.BudgetException;

public class Budget {
	
	private int userId;
	private int expenseId;
	private String expense;
	private int repeatingId;
	private String repeating;
	private double amount;
	private LocalDate date;
	private String description;
	
	public Budget(int userId, int expenseId, String expense, int repeatingId, String repeating, double amount, LocalDate date, String description) throws BudgetException {
		//if there is such an id the database:
		if(UserDAO.containsUser(userId)){
			this.userId = userId;
		}
		else{
			throw new BudgetException("Such user does not exist!");
		}
		
		if(UserHasBudgetsDAO.constainsExpense(expenseId)){
			this.expenseId = expenseId;
		}
		else{
			throw new BudgetException("There is no such an expense available!");
		}
		
		if(expense != null){
			this.expense = expense;
		}
		else{
			throw new BudgetException("There is no such kind of an expense!");
		}
		
		if(UserHasBudgetsDAO.containsRepeating(repeatingId)){
			this.repeatingId = repeatingId;
		}
		else{
			throw new BudgetException("There is no such a repeating!");
		}
		
		if(repeating != null){
			this.repeating = repeating;
		}
		else{
			throw new BudgetException("There is no such kind of repeating value!");
		}
		
		if(amount > 0){
			this.amount = amount;
		}
		else{
			throw new BudgetException("There is no such an amount!");
		}
		
		if(date != null){
			this.date = date;
		}
		else{
			throw new BudgetException("There is no such date!");
		}
		
		if(description != null){
			this.description = description;
		}
		else{
			throw new BudgetException("There is no such kind of a description!");
		}
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
