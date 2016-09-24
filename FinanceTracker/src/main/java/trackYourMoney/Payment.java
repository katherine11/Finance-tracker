package trackYourMoney;

import java.time.LocalDate;

public class Payment {
	
	private int id;
	private String category;
	private String repeating;
	private int reapeatingId;
	private double amount;
	private LocalDate date;
	private String description;
	
	public Payment (int id, String category, String repeating, int reapeatingId, double amount, LocalDate date, String description) {
		this.id = id;
		this.category = category;
		this.repeating = repeating;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.reapeatingId = reapeatingId;
	}

	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public String getRepeating() {
		return repeating;
	}

	public int getReapeatingId() {
		return reapeatingId;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}	

}
