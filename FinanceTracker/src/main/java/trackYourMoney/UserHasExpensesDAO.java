package trackYourMoney;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import connections.DBConnection;
import exceptions.ExpensesExpeption;

public class UserHasExpensesDAO {

	private static final String INSERT_EXPENSE_SQL = "insert into users_has_expenses values (?, ?, ?, ?, ?, ?)";

	public void insertExpenses(int userId, Expenses expense) throws ExpensesExpeption {

		Connection connection = DBConnection.getInstance().getConnection();

		try {
			
			PreparedStatement ps = connection.prepareStatement(INSERT_EXPENSE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, userId);
			ps.setInt(2,expense.getId());
			ps.setInt(3, expense.getReapeatingId());
			ps.setDouble(4, expense.getAmount());
			ps.setDate(5, Date.valueOf(expense.getDate()));
			ps.setString(6,expense.getDescription());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new ExpensesExpeption("Expense insert failed!");
		}
	}

	public void selectAndAddAllExpensesOfUser(User user) throws ExpensesExpeption {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select e.expenses_id, e.category, "
					+ "r.value, r.repeating_id, uhe.amount, uhe.date, uhe.description from users u "
					+ "join users_has_expenses uhe on (uhe.user_id = "+user.getUserId()+" and u.user_id = "+user.getUserId()+") "
					+ "join expenses e on (uhe.expenses_id = e.expenses_id) join repeatings r "
					+ "on (r.repeating_id = uhe.repeating_id) LIMIT 0, 1000");

			Expenses expense = null;
			List<Expenses> expenses = null;
			while (rs.next()) {
				int id = rs.getInt(1);
				String category = rs.getString(2);
				String repeating = rs.getString(3);
				int reapeatingId = rs.getInt(4);
				double amount = rs.getDouble(5);
				LocalDate date = rs.getDate(6).toLocalDate();
				String description = rs.getString(7);
				
				expense = new Expenses(id, category, repeating, reapeatingId, amount, date, description);
				user.addExpense(expense);
	
			}

		} catch (SQLException e) {
			throw new ExpensesExpeption("Expenses select failed!");
		}

	}

}
