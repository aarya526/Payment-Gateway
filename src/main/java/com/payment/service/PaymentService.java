package com.payment.service;

import java.util.List;

import com.payment.model.Payment;
import com.payment.model.PaymentCallback;
import com.payment.model.PaymentDetail;

public interface PaymentService {

	PaymentDetail proceedPayment(PaymentDetail paymentDetail);

	String payuCallback(PaymentCallback paymentResponse);

	List<Payment> findAll();

	void delete(int id);

}
