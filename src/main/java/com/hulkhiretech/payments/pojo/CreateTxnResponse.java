package com.hulkhiretech.payments.pojo;

import lombok.Data;

@Data
public class CreateTxnResponse {
        private int txnStatusId;
        private String txnReference;
}
