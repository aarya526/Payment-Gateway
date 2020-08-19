package com.payment.service;

import com.payment.model.PaymentCallback;
import com.payment.model.PaymentDetail;

public interface PaymentService {

	PaymentDetail proceedPayment(PaymentDetail paymentDetail);

	String payuCallback(PaymentCallback paymentResponse);

}
