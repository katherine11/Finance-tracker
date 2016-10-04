package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.LocalDateParser;

public abstract class Payment {
	
	private int categoryId;
	private String category;
	private String repeating;
	private int repeatingId;
	private double amount;
	private LocalDate date;
	private String description;
	private int id;
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setRepeatingId(int repeatingId) {
		this.repeatingId = repeatingId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate parsedDate = LocalDate.parse(date, formatter);
		this.date = parsedDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Payment (){
		
	}
	
	public Payment (int categoryId, String category, String repeating, int reapeatingId, double amount, LocalDate date, String description, int id) {
		this.categoryId = categoryId;
		this.category = category;
		this.repeating = repeating;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.repeatingId = reapeatingId;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getRepeating() {
		return repeating;
	}

	public int getRepeatingId() {
		return repeatingId;
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
