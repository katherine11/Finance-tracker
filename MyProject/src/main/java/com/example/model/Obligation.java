package com.example.model;

import java.time.LocalDate;

import com.example.model.exceptions.ObligationException;
import com.example.model.exceptions.PaymentException;

public class Obligation extends Payment {
	
	private static final String CHECK_IF_PERIOD_ID_EXISTS = "SELECT COUNT(period_id) FROM period WHERE period_id = ?;";
	
	private int periodId;
	private String period;
	private int periodQuantity;

	public Obligation(int categoryId, String category, String repeating, int reapeatingId, double amount,
			LocalDate date, String description, int id, String period, int periodId, int periodQuantity) throws PaymentException {
		super(categoryId, category, repeating, reapeatingId, amount, date, description, id);
		if(UserHasDAO.isValidString(period)){
			this.period = period;
		}
		else{
			throw new ObligationException("There is no such a period!");
		}
		
		setPeriodId(periodId);
		setPeriodQuantity(periodQuantity);
	}

	public Obligation() {}

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
		/*checking if the database 
		 * contains such a period
		 * */
		if(UserHasDAO.isContainedInDB(periodId, CHECK_IF_PERIOD_ID_EXISTS)){
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
