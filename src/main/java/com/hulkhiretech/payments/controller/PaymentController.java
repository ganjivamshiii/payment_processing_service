package com.hulkhiretech.payments.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hulkhiretech.payments.dao.TransactionDaoImpl;
import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {
    // ArrayList<CreateTxnRequest> dataadded = new ArrayList<>();
    @PostMapping
    public String CreatePayment(@RequestBody CreateTxnRequest createTxnRequest) {
        log.info("PaymentController initialized:{}", createTxnRequest);
        // dataadded.add(createTxnRequest);
        return "Payment created successfully";
    }
    @PostMapping("/{txnReference}/intiate")
    public String GetPaymentStatus(@PathVariable String txnReference) {
        log.info("for transaction reference: {}", txnReference);
        return "Payment status retrieved successfully"+txnReference;
    }
    // @GetMapping("/getpayments")
    // public ArrayList GetAllPayments() {
    //     log.info("Get all payments called");
    //     return dataadded;
    // }
}
