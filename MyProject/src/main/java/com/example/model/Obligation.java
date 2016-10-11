package com.example.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.example.model.exceptions.ObligationException;
import com.example.model.exceptions.PaymentException;

public class Obligation extends Payment {

	private static final String CHECK_IF_PERIOD_ID_EXISTS = "SELECT COUNT(period_id) FROM period WHERE period_id = ?;";

	private int periodId;
	private String period;
	private int periodQuantity;

	public Obligation(int categoryId, String category, String repeating, int reapeatingId, double amount,
			LocalDate date, String description, int id, String period, int periodId, int periodQuantity)
			throws PaymentException {
		super(categoryId, category, repeating, reapeatingId, amount, date, description, id);
		if (UserHasDAO.isValidString(period)) {
			this.period = period;
		} else {
			throw new ObligationException("There is no such a period!");
		}

		setPeriodId(periodId);
		setPeriodQuantity(periodQuantity);
	}

	public Obligation() {
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
		/*
		 * checking if the database contains such a period
		 */
		if (UserHasDAO.isContainedInDB(periodId, CHECK_IF_PERIOD_ID_EXISTS)) {
			this.periodId = periodId;
		} else {
			throw new ObligationException("There is no such a period!");
		}

	}

	public void setPeriodQuantity(int periodQuantity) throws ObligationException {
		if (periodQuantity > 0) {
			this.periodQuantity = periodQuantity;
		} else {
			throw new ObligationException("There is no such a period quantity!");
		}
	}

	@Override
	public String toString() {
		return super.toString() + ", Period=" + periodQuantity + " " + period;
	}

	public double getPaidAmount(){
		LocalDate begin = super.getDate();
		LocalDate end = null;
		double payment = 0;
		double amount = 0;
		switch (this.periodId){
			case 1:
				end = begin.plusDays(this.periodQuantity);
				break;
			case 2:
				end = begin.plusWeeks(this.periodQuantity);
				break;
			case 3:
				end = begin.plusMonths(this.periodQuantity);
				break;
			case 4:
				end = begin.plusYears(this.periodQuantity);
				break;
			default:
				System.err.println("Invalid Input");
				break;
		}
		long days = ChronoUnit.DAYS.between(begin, end);
		long weeks = ChronoUnit.WEEKS.between(begin, end);
		long months = ChronoUnit.MONTHS.between(begin, end);
		long years = ChronoUnit.YEARS.between(begin, end);
		LocalDate now = LocalDate.now();
		switch (super.getRepeatingId()){
			case 1:
				payment = super.getAmount();
				if (end.isBefore(now)){
					amount = payment;
				}
				break;
			case 2:
				payment = super.getAmount()/days;
				if (end.isBefore(now)){
					amount = payment;
				} else {
					amount = payment*ChronoUnit.DAYS.between(begin, now);
				}
				break;
			case 3:
				payment = super.getAmount()/weeks;
				if (end.isBefore(now)){
					amount = payment;
				} else {
					amount = payment*ChronoUnit.WEEKS.between(begin, now);
				}
				break;
			case 4:
				payment = super.getAmount()/months;
				if (end.isBefore(now)){
					amount = payment;
				} else {
					amount = payment*ChronoUnit.MONTHS.between(begin, now);
				}
				break;
			case 5:
				payment = super.getAmount()/years;
				if (end.isBefore(now)){
					amount = payment;
				} else {
					amount = payment*ChronoUnit.YEARS.between(begin, now);
				}
				break;
			default:
				System.err.println("Invalid obligation data");
				break;
		}
		return amount;	
	}

	public double getRemainedAmount() {
		return super.getAmount() - getPaidAmount();
	}

}
