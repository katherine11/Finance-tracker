package trackYourMoney;

import java.time.LocalDate;

public class Income extends Payment {

	public Income(int id, String category, String repeating, int reapeatingId, double amount, LocalDate date,
			String description) {
		super(id, category, repeating, reapeatingId, amount, date, description);
	}

}
