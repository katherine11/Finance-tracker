package com.example.model;

import java.time.LocalDate;

import com.example.model.exceptions.ObligationException;

public class Obligation extends Payment {
	
	private int periodId;
	private String period;
	private int periodQuantity;

	public Obligation(int categoryId, String category, String repeating, int reapeatingId, double amount,
			LocalDate date, String description, int id, String period, int periodId, int periodQuantity) throws ObligationException {
		super(categoryId, category, repeating, reapeatingId, amount, date, description, id);
		if(period != null & period.trim() != ""){
			this.period = period;
		}
		else{
			throw new ObligationException("There is no such a period!");
		}
		
		setPeriodId(periodId);
		setPeriodQuantity(periodQuantity);
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

	public void setPeriodId(int periodId) throws ObligationException {
		if(UserHasObligationsDAO.containsPeriod(periodId)){
			this.periodId = periodId;
		}
		else{
			throw new ObligationException("There is no such a period!");
		}
		
	}

	public void setPeriodQuantity(int periodQuantity) throws ObligationException {
		if(periodQuantity > 0){
			this.periodQuantity = periodQuantity;
		}
		else{
			throw new ObligationException("There is no such a period quantity!");
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Period="+periodQuantity+" "+period;
	}

}
