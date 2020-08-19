package com.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payment.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

	Payment findByTxnId(String txnId);
}
