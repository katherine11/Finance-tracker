package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.model.exceptions.BudgetException;
import com.example.model.exceptions.UserException;

public class Budget {

	private int userId;
	private int expenseId;
	private String expense;
	private int repeatingId;
	private String repeating;
	private double amount;
	private LocalDate date;
	private String description;

	public Budget(int userId, int expenseId, String expense, int repeatingId, String repeating, double amount,
			LocalDate date, String description) throws BudgetException {

		setUserId(userId);
		setExpenseId(expenseId);
		setExpense(expense);
		setRepeatingId(repeatingId);
		setRepeating(repeating);
		setAmount(amount);
		setDescription(description);
		if(date != null){
			this.date = date;
		}
		else{
			throw new BudgetException("There is no such date!");
		}
		
	}

	public Budget() {
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

	public void setAmount(double amount) throws BudgetException {
		if (amount > 0) {
			this.amount = amount;
		} else {
			throw new BudgetException("There is no such amount!");
		}
	}

	public void setUserId(int userId) throws BudgetException {
		/*
		 * checking if the database contains
		 * user with the user id given
		 * */
		try {
			if (UserDAO.containsUser(userId)) {
				this.userId = userId;
			}
		} catch (UserException e) {
			e.printStackTrace();
			throw new BudgetException("Such user does not exist!", e);
		}

	}

	public void setExpenseId(int expenseId) throws BudgetException {
		/*
		 * checking if the database contains
		 * expense with the expense id given
		 * */
		if (UserHasBudgetsDAO.constainsExpense(expenseId)) {
			this.expenseId = expenseId;
		} else {
			throw new BudgetException("Such an expense does not exist!");
		}

	}

	public void setExpense(String expense) throws BudgetException {
		if (expense != null && expense.trim() != "") {
			this.expense = expense;
		} else {
			throw new BudgetException("No such an expense available!");
		}
	}

	public void setRepeatingId(int repeatingId) throws BudgetException {
		/*
		 * checking if the database contains
		 * repeating with the repeating id given
		 * */
		if (UserHasBudgetsDAO.containsRepeating(repeatingId)) {
			this.repeatingId = repeatingId;
		} else {
			throw new BudgetException("There is not such a repeating!");
		}
	}

	public void setRepeating(String repeating) throws BudgetException {
		if (repeating != null && repeating.trim() != "") {
			this.repeating = repeating;
		}
		else{
			throw new BudgetException("Not a correct repeating value given");
		}
	}

	public void setDate(String date) throws BudgetException {
		/*
		 * checking for invalid values of string
		 * and parsing the date using the 
		 * formatter chosen
		 * */
		if (date != null && date.trim() != "") {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate parsedDate = LocalDate.parse(date, formatter);
			this.date = parsedDate;
		}
		else{
			throw new BudgetException("Not a correct date given");
		}
	}

	public void setDescription(String description) throws BudgetException {
		if (description != null && description.trim() != "") {
			this.description = description;
		} else {
			throw new BudgetException("Not a correct description given!");
		}
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
		return "Budget [expense=" + expense + ", repeating=" + repeating + ", amount=" + amount + ", date=" + date
				+ ", description=" + description + "]";
	}

}
