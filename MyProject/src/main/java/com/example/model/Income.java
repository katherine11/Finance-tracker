package com.example.model;

import java.time.LocalDate;

public class Income extends Payment {
	
	public Income(){
	}

	public Income(int categoryId, String category, String repeating, int reapeatingId, double amount, LocalDate date,
			String description, int id) {
		super(categoryId, category, repeating, reapeatingId, amount, date, description, id);
	}

}
