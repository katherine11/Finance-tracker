package com.example.model;

import java.time.LocalDate;

public class Obligation extends Payment {
	
	private int periodId;
	private String period;
	private int periodQuantity;

	public Obligation(int categoryId, String category, String repeating, int reapeatingId, double amount,
			LocalDate date, String description, int id, String period, int periodId, int periodQuantity) {
		super(categoryId, category, repeating, reapeatingId, amount, date, description, id);
		this.period = period;
		this.periodId = periodId;
		this.periodQuantity = periodQuantity;
	}

	public Obligation() {
		// TODO Auto-generated constructor stub
	}

	public int getPeriodId() {
		return periodId;
	}

	public String getPeriod() {
		return period;
	}

	public int getPeriodQuantity() {
		return periodQuantity;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public void setPeriodQuantity(int periodQuantity) {
		this.periodQuantity = periodQuantity;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Period="+periodQuantity+" "+period;
	}

}
