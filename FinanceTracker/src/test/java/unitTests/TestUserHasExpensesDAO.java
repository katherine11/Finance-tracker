package unitTests;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import exceptions.ExpensesExpeption;
import exceptions.UserException;
import trackYourMoney.Expenses;
import trackYourMoney.User;
import trackYourMoney.UserHasExpensesDAO;

public class TestUserHasExpensesDAO {

	private UserHasExpensesDAO userHasExpensesDAO = new UserHasExpensesDAO();
	
	@Test
	public void testUser() throws UserException, ExpensesExpeption {
		User user = new User(14, "Pesho", "pesho@abv.bg", "81dc9bdb52d04dc20036dbd8313ed055");
		
		userHasExpensesDAO.insertExpenses(14, new Expenses(1, "???", "???", 1, 20, LocalDate.now(), "test123"));
		
		
		userHasExpensesDAO.selectAndAddAllExpensesOfUser(user);
		
		Set<Expenses> expenses = user.getExpenses();
		
		System.out.println("Expenses from " + user.getUsername());
		for (Expenses expense : expenses){
			System.out.println(expense.getCategory() + ", " + expense.getAmount() + ", " 
				+ expense.getDate() + ", " + expense.getDescription() + ", " + expense.getRepeating());
		}
		
	}

}
