package com.sbw.wish.paymentact.pay

import org.json.JSONObject
import java.util.*

/**
 * PaymentData to store payment data
 */
class PaymentData : JSONObject() {

    init {
        this.put("language", Locale.getDefault().language)
    }

    fun setOrderId(orderId: String?) {
        if(!orderId.isNullOrEmpty()) this.put("orderId", orderId)
    }
    fun setAmount(amount: String?) {
        if(!amount.isNullOrEmpty()) this.put("amount", amount)
    }
    fun setEmail(email: String?) {
        if(!email.isNullOrEmpty()) this.put("email", email)
    }
    fun setCurrency(currency: String?) {
        if(!currency.isNullOrEmpty()) this.put("currency", currency)
    }
    fun setMode(mode: String?) {
        this.put("mode", mode)
    }
    fun setCardType(cardType: String?) {
        this.put("cardType", cardType)
    }
}