package com.example.model;

import com.example.model.exceptions.PaymentExpeption;

public interface UserHasDAO {

	int insertPayment(int userId, Payment payment) throws PaymentExpeption;

	void selectAndAddAllPaymentsOfUser(User user) throws PaymentExpeption;

	boolean deletePayment(int id) throws PaymentExpeption;

}