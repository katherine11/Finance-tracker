package com.example.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.example.model.exceptions.UserException;

public class User {

	private int userId;
	private String username;
	private String password;
	@NotNull
	@Email
	private String email;
	private Set<Payment> expenses = new LinkedHashSet<Payment>(); 
	private Set<Payment> incomes = new LinkedHashSet<Payment>(); 
	private Set<Payment> obligations = new LinkedHashSet<Payment>(); 
	private Set<Budget> budgets = new LinkedHashSet<Budget>(); 
	private double balance;
	private double totalIncomes;
	private double totalExpenses;

	public User() {
	}

	
	public User(int id, String username,String email, String password) {
		if (isValidString(username) || isValidString(email) || isValidString(password)){
			try {
				throw new UserException("Invalid user data!");
			} catch (UserException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		this.userId = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	
	boolean isValidString(String string) {
		return string == null || string.trim().equals("");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean addExpense(Expense expense){
//		this.balance -= expense.getAmount();
		return this.expenses.add(expense);
	}
	public boolean addIncome(Income income){
//		this.balance += income.getAmount();
		return this.incomes.add(income);
	}
	public boolean addObligation(Obligation obligation){
		return this.obligations.add(obligation);
	}
	public boolean addBudget(Budget budget){
		return this.budgets.add(budget);
	}
	
	public Set<Payment> getExpenses() {
		return Collections.unmodifiableSet(expenses);
	}
	public Set<Payment> getIncomes() {
		return Collections.unmodifiableSet(incomes);
	}
	public Set<Payment> getObligations() {
		return Collections.unmodifiableSet(obligations);
	}
	public Set<Budget> getBudgets() {
		return Collections.unmodifiableSet(budgets);
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void removeIncome(int id) {
		for (Payment income : incomes){
			if (income.getId() == id){
				incomes.remove(income);				
				return;
			}
		}
	}
	
	public void removeExpense(int id) {
		for (Payment expense : expenses){
			if (expense.getId() == id){
				expenses.remove(expense);				
				return;
			}
		}
		
	}
	
	public double getBalance () {
	
		this.balance += getTotalIncomes();
		this.balance -= getTotalExpenses();
		
		return this.balance;
		
	}

	public double getTotalExpenses() {
		double totalExpenses = 0;
		for (Payment expense : expenses){
			totalExpenses += expense.getAmount();
		}
		return totalExpenses;
	}

	public double getTotalIncomes() {
		double totalIncomes = 0;
		for (Payment income : incomes){
			totalIncomes += income.getAmount();
		}
		return totalIncomes;
	}


	
	
}

