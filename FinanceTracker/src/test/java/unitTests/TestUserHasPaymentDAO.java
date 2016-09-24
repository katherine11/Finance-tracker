package unitTests;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Test;

import exceptions.PaymentExpeption;
import exceptions.UserException;
import trackYourMoney.Expense;
import trackYourMoney.Income;
import trackYourMoney.Payment;
import trackYourMoney.User;
import trackYourMoney.UserHasDAO;
import trackYourMoney.UserHasExpensesDAO;
import trackYourMoney.UserHasIncomesDAO;

public class TestUserHasPaymentDAO {

	private static final String INCOME_DESCRIPTION = "Income Description text";
	private static final int INCOME_AMOUNT = 350;
	private static final int INCOME_ID = 7;
	private static final int USER_ID = 14;
	private static final String EXPENSE_DESCRIPTION = "Expense description text";
	private static final int AMOUNT_EXPENSE = 20;
	private static final int REPEATING_ID = 1;
	private static final int EXPENSE_ID = 1;
	
	private UserHasDAO userHasExpensesDAO = new UserHasExpensesDAO();
	private UserHasDAO userHasIncomesDAO = new UserHasIncomesDAO();

	@Test
	public void testUser() throws UserException, PaymentExpeption {
		User user = new User(USER_ID, "Pesho", "pesho@abv.bg", "81dc9bdb52d04dc20036dbd8313ed055");

		userHasExpensesDAO.insertPayment(USER_ID, new Expense(EXPENSE_ID, "???", "???", REPEATING_ID, AMOUNT_EXPENSE, LocalDate.now(), EXPENSE_DESCRIPTION));
		userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
		Set<Payment> expenses = user.getExpenses();
		System.out.println("Expenses from " + user.getUsername());
		printPayments(expenses);
		
		userHasIncomesDAO.insertPayment(USER_ID, new Income(INCOME_ID, "???", "???", REPEATING_ID, INCOME_AMOUNT , LocalDate.now(), INCOME_DESCRIPTION));
		userHasIncomesDAO.selectAndAddAllPaymentsOfUser(user);
		Set<Payment> incomes = user.getIncomes();
		System.out.println("Incomes from " + user.getUsername());
		printPayments(incomes);

	}

	void printPayments(Set<Payment> payments) {
		for (Payment payment : payments) {
			System.out.println(payment.getCategory() + ", " + payment.getAmount() + ", " + payment.getDate() + ", "
					+ payment.getDescription() + ", " + payment.getRepeating());
		}
	}

}
