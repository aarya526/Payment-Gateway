package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.payment.model.Payment;
import com.payment.model.PaymentCallback;
import com.payment.model.PaymentMode;
import com.payment.model.PaymentStatus;
import com.payment.service.PaymentService;

@Controller
public class HomeController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/payment")
	public ModelAndView viewPaymentPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("paymentview");
		return model;
	}

	@GetMapping("/")
	public String getPaymentList() {

		return "payments";

	}

	@GetMapping("/payment-status")
	public String getPaymentStatusDetail(@ModelAttribute Payment payment, Model model) {

		model.addAttribute("payment", payment);
		System.out.println("payment Status : " + payment.getPaymentStatus());
		return "paymentstatus";

	}

	@RequestMapping(path = "/payment/payment-response", method = RequestMethod.POST)
	public String payuCallback(@RequestParam String mihpayid, @RequestParam String status,
			@RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash, Model model) {
		PaymentCallback paymentCallback = new PaymentCallback();
		paymentCallback.setMihpayid(mihpayid);
		paymentCallback.setTxnid(txnid);
		paymentCallback.setMode(mode);
		paymentCallback.setHash(hash);
		paymentCallback.setStatus(status);
		Payment payment = paymentService.payuCallback(paymentCallback);
		model.addAttribute("payment", payment);
		return "paymentstatus";
	}

}
