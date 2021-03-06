package com.payment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.model.Payment;
import com.payment.model.PaymentCallback;
import com.payment.model.PaymentDetail;
import com.payment.model.PaymentStatus;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;
import com.payment.utility.PaymentUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentDetail proceedPayment(PaymentDetail paymentDetail) {
		PaymentUtil paymentUtil = new PaymentUtil();
		paymentDetail = paymentUtil.populatePaymentDetail(paymentDetail);
		System.out.println("Hash : " + paymentDetail.getHash());
		savePaymentDetail(paymentDetail);
		return paymentDetail;
	}

	@Override
	public Payment payuCallback(PaymentCallback paymentResponse) {
		String msg = "Transaction failed.";
		Payment payment = paymentRepository.findByTxnId(paymentResponse.getTxnid());
		if (payment != null) {
			// TODO validate the hash
			PaymentStatus paymentStatus = null;
			if (paymentResponse.getStatus().equals("failure")) {
				paymentStatus = PaymentStatus.Failed;
			} else if (paymentResponse.getStatus().equals("success")) {
				paymentStatus = PaymentStatus.Success;
				msg = "Transaction success";
			} else if (paymentResponse.getStatus().equals("pending")) {
				paymentStatus = PaymentStatus.Pending;
				msg = "Transaction Pending";
			}
			payment.setPaymentStatus(paymentStatus);
			payment.setMihpayId(paymentResponse.getMihpayid());
			payment.setMode(paymentResponse.getMode());
			paymentRepository.save(payment);
		}
		return payment;
	}

	private void savePaymentDetail(PaymentDetail paymentDetail) {
		Payment payment = new Payment();
		payment.setAmount(Double.parseDouble(paymentDetail.getAmount()));
		payment.setEmail(paymentDetail.getEmail());
		payment.setName(paymentDetail.getName());
		payment.setPaymentDate(new Date());
		payment.setPaymentStatus(PaymentStatus.Pending);
		payment.setPhone(paymentDetail.getPhone());
		payment.setProductInfo(paymentDetail.getProductInfo());
		payment.setTxnId(paymentDetail.getTxnId());
		paymentRepository.save(payment);
	}

	@Override
	public List<Payment> findAll() {
		return (List<Payment>) paymentRepository.findAll();

	}

	@Override
	public void delete(int id) {
		Payment payment = paymentRepository.findById(id).orElse(null);
		paymentRepository.delete(payment);

	}
}
