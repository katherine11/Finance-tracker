package com.example.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.example.model.connections.DBConnection;
import com.example.model.exceptions.PaymentExpeption;

public class UserHasBudgetsDAO  {
	
	private static final String DELETE_BUDGET_SQL = "DELETE FROM users_has_budgets WHERE user_id = ? and expense_id = ?;";
	private static final String INSERT_BUDGET_SQL = "insert into users_has_budgets values (?, ?, ?, ?, ?, ?)";

	public boolean insertBudget(Budget budget) throws PaymentExpeption {
		Connection connection = DBConnection.getInstance().getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(INSERT_BUDGET_SQL);

			ps.setInt(1, budget.getUserId());
			ps.setInt(2, budget.getExpenseId());
			ps.setInt(3, budget.getRepeatingId());
			ps.setDouble(4, budget.getAmount());
			ps.setDate(5, Date.valueOf(budget.getDate()));
			ps.setString(6, budget.getDescription());

			int insert = ps.executeUpdate();
			
			return (insert >=1);

		} catch (SQLException e) {
			throw new PaymentExpeption("Budget insert failed!");
		}
	}

	public void selectAndAddAllBudgetsOfUser(User user) throws PaymentExpeption {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select e.expenses_id, e.category, r.repeating_id, r.value, uhb.amount, "
					+ "uhb.date, uhb.description from users u "
					+ "join users_has_budgets uhb on (uhb.user_id = "+user.getUserId()+" and u.user_id = "+user.getUserId()+") "
					+ "join expenses e on (uhb.expense_id = e.expenses_id) "
					+ "join repeatings r on (r.repeating_id = uhb.repeating_id) LIMIT 0, 1000");

			Budget budget = null;

			while (rs.next()) {
				int expenseId = rs.getInt(1);
				String expense = rs.getString(2);	
				int repeatingId = rs.getInt(3);
				String repeating = rs.getString(4);
				double amount = rs.getDouble(5);
				LocalDate date = rs.getDate(6).toLocalDate();
				String description = rs.getString(7);

				budget = new Budget(user.getUserId(), expenseId, expense, repeatingId, repeating, amount, date, description);
				user.addBudget(budget);

			}

		} catch (SQLException e) {
			throw new PaymentExpeption("Budgets select failed!");
		}
	}

	public boolean deleteBudget(int userID, int expenseId) throws PaymentExpeption {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_BUDGET_SQL);

			ps.setInt(1, userID);
			ps.setInt(2, expenseId);
			
			int deletedRows = ps.executeUpdate(); 
			
			if (deletedRows == 0){
				throw new PaymentExpeption("No such Budget!");
			}
			return true;

		} catch (SQLException e) {
			throw new PaymentExpeption ("Someting went wrong!");
		} 
	}

}
