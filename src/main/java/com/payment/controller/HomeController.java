package com.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

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

}
