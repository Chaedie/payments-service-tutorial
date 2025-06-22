package com.chaedie.paymentsservice.payment.adapter.`in`.web.request

data class TossPaymentConfirmRequest(
    val paymentKey: String,
    val orderId: String,
    val amount: Long
)
