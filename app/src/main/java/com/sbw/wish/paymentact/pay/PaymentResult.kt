package com.sbw.wish.paymentact.pay

import org.json.JSONObject

// PaymentResult to store payment result
class PaymentResult : JSONObject(){
    fun getErrorCode(): Int {
        return this.getInt("errorCode")
    }
    fun setErrorCode(errorCode: Int?){
        this.put("errorCode", errorCode)
    }

    fun getCause(): String {
        return this.getString("cause")
    }
    fun setCause(cause: String?){
        this.put("cause", cause)
    }

    fun isSuccess(): Boolean {
        return this.getBoolean("success")
    }
    fun setSuccess(success: Boolean){
        this.put("success", success)
    }
}