package trackYourMoney;

import exceptions.PaymentExpeption;

public abstract class UserHasDAO {
	
//	private static final String INSERT_TEXT_SQL = "insert into table_name values (?, ?, ?, ?, ?, ?, null)";

	public abstract int insertPayment(int userId, Payment payment) throws PaymentExpeption;

	public abstract void selectAndAddAllPaymentsOfUser(User user) throws PaymentExpeption;
	
	public abstract boolean deletePayment (int id) throws PaymentExpeption;
}
