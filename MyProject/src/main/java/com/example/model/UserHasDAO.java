package com.example.model;

import com.example.model.exceptions.PaymentException;

public interface UserHasDAO {

	int insertPayment(int userId, Payment payment) throws PaymentException;

	void selectAndAddAllPaymentsOfUser(User user) throws PaymentException;

	boolean deletePayment(int id) throws PaymentException;

}