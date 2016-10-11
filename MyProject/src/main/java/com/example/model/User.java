package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import com.example.model.exceptions.BudgetException;
import com.example.model.exceptions.ExpenseException;
import com.example.model.exceptions.IncomeException;
import com.example.model.exceptions.ObligationException;
import com.example.model.exceptions.UserException;

public class User {

	private static final int DAYS_OF_THE_WEEK = 7;
	private static final int MAX_REPEATING_ID = 5;
	private static final int _5 = 5;
	private static final int MIN_REPEATING_ID = 1;
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

	public User() {
	}

	public User(int id, String username, String email, String password) {
		/*
		 * throwing an exception in case of invalid data given
		 */
		if (isValidString(username) || isValidString(email) || isValidString(password)) {
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

	public boolean addExpense(Expense expense) throws ExpenseException {
		if (expense != null) {
			// this.balance -= expense.getAmount();
			return this.expenses.add(expense);
		} else {
			throw new ExpenseException("Invalid expense given!");
		}
	}

	public boolean addIncome(Income income) throws IncomeException {
		if (income != null) {
			// this.balance += income.getAmount();
			return this.incomes.add(income);
		} else {
			throw new IncomeException("Invalid income given");
		}
	}

	public boolean addObligation(Obligation obligation) throws ObligationException {
		if (obligation != null) {
			return this.obligations.add(obligation);
		} else {
			throw new ObligationException("Invalid obligation given!");
		}
	}

	public boolean addBudget(Budget budget) throws BudgetException {
		if (budget != null) {
			return this.budgets.add(budget);
		} else {
			throw new BudgetException("Invalid budget given!");
		}
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

	public void setUsername(String username) throws UserException {
		if (UserHasDAO.isValidString(username)) {
			this.username = username;
		} else {
			throw new UserException("Invalid username given!");
		}
	}

	public void setPassword(String password) throws UserException {
		if (UserHasDAO.isValidString(password)) {
			this.password = password;
		} else {
			throw new UserException("Invalid password given!");
		}
	}

	public void setEmail(String email) throws UserException {
		if (UserHasDAO.isValidString(email)) {
			this.email = email;
		} else {
			throw new UserException("Invalid email given!");
		}
	}

	public void removeIncome(int id) throws UserException {
		if (isValidId(id) && id < incomes.size()) {
			for (Payment income : incomes) {
				if (income.getId() == id) {
					incomes.remove(income);
					return;
				}
			}
		} else {
			throw new UserException("Invalid id given - cannot remove the income!");
		}
	}

	private boolean isValidId(int id) {
		return id > 0;
	}

	public void removeExpense(int id) throws UserException {
		if (isValidId(id) && id < this.expenses.size()) {
			for (Payment expense : this.expenses) {
				if (expense.getId() == id) {
					expenses.remove(expense);
					return;
				}
			}

		} else {
			throw new UserException("Invalid id given - cannot remove the expense!");
		}
	}

	public void removeBudget(int expenseId) throws UserException {
		if (isValidId(expenseId) && expenseId < this.budgets.size()) {
			for (Budget budget : this.budgets) {
				if (budget.getExpenseId() == expenseId) {
					this.budgets.remove(budget);
					return;
				}
			}
		} else {
			throw new UserException("Invalid id given - cannot remove the budget!");
		}
	}

	public void removeObligation(int id) throws UserException {
		if (isValidId(id) && id < this.obligations.size()) {
			for (Payment obligation : this.obligations) {
				if (obligation.getId() == id) {
					this.obligations.remove(obligation);
					return;
				}
			}

		} else {
			throw new UserException("Invalid id given - cannot remove the obligation!");
		}
	}

	public double getBalance() {
		double balance = getTotalIncomes() - getTotalExpenses(this.expenses);

		return balance;

	}

	public double getTotalExpenses(Collection<Payment> expenses) {
		LocalDate now = LocalDate.now();
		double totalExpenses = 0;
		for (Payment expense : expenses) {
			LocalDate expenseDate = expense.getDate();
			switch (expense.getRepeatingId()) {
			case 2:
				for (LocalDate date = expenseDate; !date.isAfter(now); date = date.plusDays(1)) {
					totalExpenses += expense.getAmount();
					System.out.println("DAILY");
				}
				break;
			case 3:
				for (LocalDate date = expenseDate; !date.isAfter(now); date = date.plusDays(7)) {
					totalExpenses += expense.getAmount();
					System.out.println("WEEKLY");
				}
				break;
			case 4:
				for (LocalDate date = expenseDate; !date.isAfter(now); date = date.plusMonths(1)) {
					totalExpenses += expense.getAmount();
					System.out.println("MONTHLY");
				}
				break;
			case 5:
				for (LocalDate date = expenseDate; !date.isAfter(now); date = date.plusYears(1)) {
					totalExpenses += expense.getAmount();
					System.out.println("YEARLY");
				}
				break;
			default:
				totalExpenses += expense.getAmount();
				break;
			}
		}
		return totalExpenses;
	}

	public double getTotalIncomes() {
		double totalIncomes = 0;
		for (Payment income : this.incomes) {
			totalIncomes += income.getAmount();
		}
		return totalIncomes;
	}

	public double getTotalBudgets() {
		double totalAmount = 0;
		for (Budget budget : this.budgets) {
			totalAmount += budget.getAmount();
		}
		return totalAmount;
	}

	public double getTotalObligations() {
		double totalAmount = 0;
		for (Payment obligation : this.obligations) {
			totalAmount += obligation.getAmount();
		}
		return totalAmount;
	}

	// <<<<<<< HEAD
	// public List<Payment> getExpensesBy(String from, String to, int
	// categoryId) {
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	// LocalDate parsedDateFrom = LocalDate.parse(from, formatter);
	// LocalDate parsedDateTo = LocalDate.parse(to, formatter);
	// List<Payment> ExpensesBy = new LinkedList<Payment>();
	// for (Payment expense : expenses) {
	// LocalDate expenseDate = expense.getDate();
	// if ((!expenseDate.isBefore(parsedDateFrom) || expense.getRepeatingId() >
	// 1)
	// && !expenseDate.isAfter(parsedDateTo)) {
	// if (expense.getRepeatingId() == 1) {
	// if (categoryId != 0) {
	// if (expense.getCategoryId() == categoryId) {
	// ExpensesBy.add(expense);
	// =======
public List<Payment> getExpensesBy(String from, String to, int categoryId) throws UserException {
		
		//must add a validation for category id!
		
		/*checking if the data given are valid 
		 * then parsing the dates given to LocalDate
		 * in order to find the expenses which date is
		 * between the initial and the end date
		 * by a specific category given 
		 * */
		
		if(UserHasDAO.isValidString(from) && UserHasDAO.isValidString(to)){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate parsedDateFrom = LocalDate.parse(from, formatter);
			LocalDate parsedDateTo = LocalDate.parse(to, formatter);
			List<Payment> ExpensesBy = new LinkedList<Payment>();
			for (Payment expense : expenses) {
				LocalDate expenseDate = expense.getDate();
				if ((!expenseDate.isBefore(parsedDateFrom) || expense.getRepeatingId() > 1)
						&& !expenseDate.isAfter(parsedDateTo)) {
					if (expense.getRepeatingId() == 1) {
						if (categoryId != 0) {
							if (expense.getCategoryId() == categoryId) {
								ExpensesBy.add(expense);
							}
							continue;
						}
						ExpensesBy.add(expense);
					}
					if (expense.getRepeatingId() == 2) {
						Expense expenseToAdd = null;
						for (LocalDate date = expenseDate; !date.isAfter(parsedDateTo); date = date.plusDays(1)) {
							if (!date.isBefore(parsedDateFrom) && !date.isAfter(parsedDateTo)) {
								try {
									expenseToAdd = (Expense) expense.getCopy();
									expenseToAdd.setLocalDate(date);
								} catch (CloneNotSupportedException e) {
									e.printStackTrace();
								}
								if (categoryId != 0) {
									if (expense.getCategoryId() == categoryId) {
										ExpensesBy.add(expenseToAdd);
									}
									continue;
								}
								ExpensesBy.add(expenseToAdd);
							}
						}
					}
					if (expense.getRepeatingId() == 3) {
						for (LocalDate date = expenseDate; !date.isAfter(parsedDateTo); date = date.plusDays(7)) {
							if (!date.isBefore(parsedDateFrom) && !date.isAfter(parsedDateTo)) {
								if (categoryId != 0) {
									if (expense.getCategoryId() == categoryId) {
										ExpensesBy.add(expense);
										continue;
									}
									ExpensesBy.add(expense);
								}
							}
						}
					}
					if (expense.getRepeatingId() == 4) {
						for (LocalDate date = expenseDate; !date.isAfter(parsedDateTo); date = date.plusMonths(1)) {
							if (!date.isBefore(parsedDateFrom) && !date.isAfter(parsedDateTo)) {
								if (categoryId != 0) {
									if (expense.getCategoryId() == categoryId) {
										ExpensesBy.add(expense);
									}
									continue;
								}
								ExpensesBy.add(expense);
						
							}
						}
					}
					if (expense.getRepeatingId() == 5) {
						for (LocalDate date = expenseDate; date.isAfter(parsedDateTo); date = date.plusYears(1)) {
							if (!date.isBefore(parsedDateFrom) && !date.isAfter(parsedDateTo)) {
								if (categoryId != 0) {
									if (expense.getCategoryId() == categoryId) {
										ExpensesBy.add(expense);
									}
									continue;
								}
								ExpensesBy.add(expense);
								break;
							}
						}
					}
				}
			}
			return ExpensesBy;
		}
		else{
			throw new UserException("Invalid data given!");
		}
	}

	/*
	 * getting the total amount of money for expenses having a given category
	 */
	public double getAmoutByExpenseCategoryId(int categoryId) {
		double exp = 0;
		for (Payment expense : this.expenses) {
			if (expense.getCategoryId() == categoryId) {
				exp += expense.getAmount();
			}
		}
		return exp;
	}

	/*
	 * getting the total amount of money for incomes having a given category
	 */
	public double getAmoutByIncomeCategoryId(int categoryId) {
		double inc = 0;
		for (Payment income : this.incomes) {
			if (income.getCategoryId() == categoryId) {
				inc += income.getAmount();
			}
		}
		return inc;
	}

	/*
	 * getting the remaining amount of money of the budgets for a given
	 * category, calculating all budgets amount and then removing the amount of
	 * money given as expenses
	 */
	public double getRemainAmountForBudget(int expenseId) {
		double budgetExpenses = 0;
		for (Budget budget : this.budgets) {
			if (budget.getExpenseId() == expenseId) {
				budgetExpenses += budget.getAmount();
			}
		}
		for (Payment expense : this.expenses) {
			if (expense.getCategoryId() == expenseId) {
				budgetExpenses -= expense.getAmount();
			}
		}
		return budgetExpenses;

	}

	/*
	 * getting all money for expenses for the current month by going through the
	 * expenses and checking
	 */
	public double getExpensesForMonth() {
		double expensesForMonth = 0;
		for (Payment expense : this.expenses) {
			LocalDate now = LocalDate.now();
			LocalDate expenseDate = expense.getDate();
			if ((expenseDate.getYear() == now.getYear() || expense.getRepeatingId() > 1)
					&& (expenseDate.getMonthValue() == now.getMonthValue()
							|| (expense.getRepeatingId() > MIN_REPEATING_ID
									&& expense.getRepeatingId() < MAX_REPEATING_ID))
					&& expenseDate.getDayOfMonth() <= now.getDayOfMonth()
					|| (expense.getRepeatingId() > MIN_REPEATING_ID && expense.getRepeatingId() < 4)) {
				int passedDays = now.getDayOfMonth();
				switch (expense.getRepeatingId()) {
				case 1:
					expensesForMonth += expense.getAmount();
					break;
				case 2:
					for (int repeat = 1; repeat <= passedDays; repeat++) {
						expensesForMonth += expense.getAmount();
					}
					break;
				case 3:
					for (int repeat = 1; repeat <= passedDays; repeat += DAYS_OF_THE_WEEK) {
						expensesForMonth += expense.getAmount();
					}
					break;
				default:
					expensesForMonth += expense.getAmount();
					break;
				}
			}

		}
		return expensesForMonth;
	}

	/*
	 * getting all incomes for the current month
	 */
	public double getIncomesForMonth() {
		double incomesForMonth = 0;
		for (Payment income : incomes) {
			LocalDate now = LocalDate.now();
			LocalDate incomeDate = income.getDate();
			if ((incomeDate.getYear() == now.getYear() || income.getRepeatingId() > MIN_REPEATING_ID)
					&& (incomeDate.getMonthValue() == now.getMonthValue() || (income.getRepeatingId() > MIN_REPEATING_ID
							&& income.getRepeatingId() < MAX_REPEATING_ID))
					&& incomeDate.getDayOfMonth() <= now.getDayOfMonth()
					|| (income.getRepeatingId() > MIN_REPEATING_ID && income.getRepeatingId() < 4)) {
				int passedDays = now.getDayOfMonth();
				switch (income.getRepeatingId()) {
				case 1:
					incomesForMonth += income.getAmount();
					break;
				case 2:
					for (int repeat = incomeDate.getDayOfMonth(); repeat <= passedDays; repeat++) {
						incomesForMonth += income.getAmount();
					}
					break;
				case 3:
					for (int repeat = incomeDate.getDayOfMonth(); repeat <= passedDays; repeat += DAYS_OF_THE_WEEK) {
						incomesForMonth += income.getAmount();
					}
					break;
				default:
					incomesForMonth += income.getAmount();
					break;
				}
			}

		}
		return incomesForMonth;
	}

	public double getBalanceForMonth() {
		double balanceForMonth = getIncomesForMonth() - getExpensesForMonth();
		return balanceForMonth;
	}

}
