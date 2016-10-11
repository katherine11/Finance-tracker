package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	public User() {
	}

	public User(int id, String username, String email, String password) {
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

	public boolean addExpense(Expense expense) {
		// this.balance -= expense.getAmount();
		return this.expenses.add(expense);
	}

	public boolean addIncome(Income income) {
		// this.balance += income.getAmount();
		return this.incomes.add(income);
	}

	public boolean addObligation(Obligation obligation) {
		return this.obligations.add(obligation);
	}

	public boolean addBudget(Budget budget) {
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
		for (Payment income : incomes) {
			if (income.getId() == id) {
				incomes.remove(income);
				return;
			}
		}
	}

	public void removeExpense(int id) {
		for (Payment expense : expenses) {
			if (expense.getId() == id) {
				expenses.remove(expense);
				return;
			}
		}

	}

	public void removeBudget(int expenseId) {
		for (Budget budget : budgets) {
			if (budget.getExpenseId() == expenseId) {
				budgets.remove(budget);
				return;
			}
		}
	}

	public void removeObligation(int id) {
		for (Payment obligation : obligations) {
			if (obligation.getId() == id) {
				obligations.remove(obligation);
				return;
			}
		}

	}

	public double getBalance() {
		double balance = getTotalIncomes() - getTotalExpenses(this.expenses);

		return balance;

	}

	public double getTotalExpenses(Set<Payment> expenses) {
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
		for (Payment income : incomes) {
			totalIncomes += income.getAmount();
		}
		return totalIncomes;
	}

	public double getTotalBudgets() {
		double totalAmount = 0;
		for (Budget budget : budgets) {
			totalAmount += budget.getAmount();
		}
		return totalAmount;
	}

	public double getTotalObligations() {
		double totalAmount = 0;
		for (Payment obligation : obligations) {
			totalAmount += obligation.getAmount();
		}
		return totalAmount;
	}

	public Set<Payment> getExpensesBy(String from, String to, int categoryId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate parsedDateFrom = LocalDate.parse(from, formatter);
		LocalDate parsedDateTo = LocalDate.parse(to, formatter);
		Set<Payment> ExpensesBy = new LinkedHashSet<Payment>();
		for (Payment expense : expenses) {
			LocalDate expenseDate = expense.getDate();
			if ((!expenseDate.isBefore(parsedDateFrom) || expense.getRepeatingId() > 1)
					&& !expenseDate.isAfter(parsedDateTo)) {
				if (expense.getRepeatingId() == 1 || expense.getRepeatingId() == 2) {
					if (categoryId != 0) {
						if (expense.getCategoryId() == categoryId) {
							ExpensesBy.add(expense);
						}
						continue;
					}
					ExpensesBy.add(expense);
				}
				if (expense.getRepeatingId() == 3) {
					for (LocalDate date = expenseDate; date.isAfter(parsedDateTo); date = date.plusDays(7)) {
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
							break;
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

	public double getAmoutByExpenseCategoryId(int categoryId) {
		double exp = 0;
		for (Payment expense : expenses) {
			if (expense.getCategoryId() == categoryId) {
				exp += expense.getAmount();
			}
		}
		return exp;
	}

	public double getAmoutByIncomeCategoryId(int categoryId) {
		double inc = 0;
		for (Payment income : incomes) {
			if (income.getCategoryId() == categoryId) {
				inc += income.getAmount();
			}
		}
		return inc;
	}

	public double getRemainAmountForBudget(int expenseId) {
		double budgetExpenses = 0;
		for (Budget budget : budgets) {
			if (budget.getExpenseId() == expenseId) {
				budgetExpenses += budget.getAmount();
			}
		}
		for (Payment expense : expenses) {
			if (expense.getCategoryId() == expenseId) {
				budgetExpenses -= expense.getAmount();
			}
		}
		return budgetExpenses;

	}

	public double getExpensesForMonth() {
		double expensesForMonth = 0;
		LocalDate now = LocalDate.now();
		for (Payment expense : expenses) {
			LocalDate expenseDate = expense.getDate();
			if ((expenseDate.getYear() == now.getYear() || expense.getRepeatingId() > 1)
					&& (expenseDate.getMonthValue() == now.getMonthValue()
							|| (expense.getRepeatingId() > 1 && expense.getRepeatingId() < 5))
					&& expenseDate.getDayOfMonth() <= now.getDayOfMonth()
					|| (expense.getRepeatingId() > 1 && expense.getRepeatingId() < 4)) {
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
					for (int repeat = 1; repeat <= passedDays; repeat += 7) {
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

	public double getIncomesForMonth() {
		double incomesForMonth = 0;
		for (Payment income : incomes) {
			LocalDate now = LocalDate.now();
			LocalDate incomeDate = income.getDate();
			if ((incomeDate.getYear() == now.getYear() || income.getRepeatingId() > 1)
					&& (incomeDate.getMonthValue() == now.getMonthValue()
							|| (income.getRepeatingId() > 1 && income.getRepeatingId() < 5))
					&& incomeDate.getDayOfMonth() <= now.getDayOfMonth()
					|| (income.getRepeatingId() > 1 && income.getRepeatingId() < 4)) {
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
					for (int repeat = incomeDate.getDayOfMonth(); repeat <= passedDays; repeat += 7) {
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
