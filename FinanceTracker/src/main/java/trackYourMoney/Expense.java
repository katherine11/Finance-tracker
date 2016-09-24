package trackYourMoney;

import java.time.LocalDate;

public class Expense extends Payment {

	public Expense(int categoryId, String category, String repeating, int reapeatingId, double amount, LocalDate date,
			String description, int id) {
		super(categoryId, category, repeating, reapeatingId, amount, date, description, id);
	}

}
