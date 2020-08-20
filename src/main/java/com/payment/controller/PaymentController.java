package com.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.model.Payment;
import com.payment.model.PaymentCallback;
import com.payment.model.PaymentDetail;
import com.payment.model.PaymentMode;
import com.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping(path = "/payment-details")
	public ResponseEntity<?> proceedPayment(@RequestBody PaymentDetail paymentDetail) {
		return new ResponseEntity<PaymentDetail>(paymentService.proceedPayment(paymentDetail), HttpStatus.OK);
	}

	@RequestMapping(path = "/payment-response", method = RequestMethod.POST)
	public ResponseEntity<?> payuCallback(@RequestParam String mihpayid, @RequestParam String status,
			@RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash) {
		PaymentCallback paymentCallback = new PaymentCallback();
		paymentCallback.setMihpayid(mihpayid);
		paymentCallback.setTxnid(txnid);
		paymentCallback.setMode(mode);
		paymentCallback.setHash(hash);
		paymentCallback.setStatus(status);
		return new ResponseEntity<String>(paymentService.payuCallback(paymentCallback), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<?> payments() {

		List<Payment> payments = paymentService.findAll();
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> proceedPayment(@PathVariable int id) {
		paymentService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
