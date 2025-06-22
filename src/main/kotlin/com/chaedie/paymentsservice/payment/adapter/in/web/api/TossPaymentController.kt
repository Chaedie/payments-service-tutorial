package com.chaedie.paymentsservice.payment.adapter.`in`.web.api

import com.chaedie.paymentsservice.common.WebAdapter
import com.chaedie.paymentsservice.payment.adapter.`in`.web.request.TossPaymentConfirmRequest
import com.chaedie.paymentsservice.payment.adapter.`in`.web.response.ApiResponse
import com.chaedie.paymentsservice.payment.adapter.`in`.web.view.PaymentController
import com.chaedie.paymentsservice.payment.adapter.out.web.executor.TossPaymentExecutor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@WebAdapter
@RequestMapping("/v1/toss")
@RestController
class TossPaymentController(
    private val tossPaymentExecutor: TossPaymentExecutor,
    private val paymentController: PaymentController,
) {

    @PostMapping("/confirm")
    fun confirm(@RequestBody request: TossPaymentConfirmRequest): Mono<ResponseEntity<ApiResponse<String>>> {
        return tossPaymentExecutor.execute(
            paymentKey = request.paymentKey,
            orderId = request.orderId,
            amount = request.amount.toString()
        ).map {
            ResponseEntity.ok().body(
                ApiResponse.with(HttpStatus.OK, "Ok", it)
            )
        }
    }
}
