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
import exceptions.PaymentExpeption;

public abstract class UserHasDAO {
	
	private static final String INSERT_TEXT_SQL = "insert into table_name values (?, ?, ?, ?, ?, ?, null)";

	public abstract int insertPayment(int userId, Payment payment) throws PaymentExpeption;

	public abstract void selectAndAddAllPaymentsOfUser(User user) throws PaymentExpeption;
	
	public abstract boolean deletePayment (int id);
}
