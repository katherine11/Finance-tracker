package trackYourMoney;

import java.time.LocalDate;

public class Payment {
	
	private int categoryId;
	private String category;
	private String repeating;
	private int reapeatingId;
	private double amount;
	private LocalDate date;
	private String description;
	private int id;
	
	public Payment (int categoryId, String category, String repeating, int reapeatingId, double amount, LocalDate date, String description, int id) {
		this.categoryId = categoryId;
		this.category = category;
		this.repeating = repeating;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.reapeatingId = reapeatingId;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	protected int getCategoryId() {
		return categoryId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Payment other = (Payment) obj;
		if (id != other.id)
			return false;
		return true;
	}	

}
