package com.sbw.wish.paymentact.pay

/**
 * Payment error codes
 */
object PaymentErrorCode {
    // Unknown error
    const val UNKNOWN_ERROR = 1

    // Timeout error
    const val TIMEOUT_ERROR = 2

    // No connection error
    const val NO_CONNECTION_ERROR = 3

    // Server error
    const val SERVER_ERROR = 4

    // Payment cancelled error
    const val PAYMENT_CANCELLED_ERROR = 5

    // Payment refused error
    const val PAYMENT_REFUSED_ERROR = 6
}