package com.sbw.wish.paymentact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.sbw.wish.paymentact.pay.AbstractPaymentActivity
import com.sbw.wish.paymentact.pay.PaymentProvider
import com.sbw.wish.paymentact.pay.PaymentResult

// Merchant server url
// FIXME: change by the right payment server
private const val SERVER_URL="http://192.168.0.105:4200/"

class MainActivity : AbstractPaymentActivity(), View.OnClickListener {

    private lateinit var btn_pay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btn_pay = findViewById(R.id.btn_pay)
    }

    override fun onStart() {
        super.onStart()
        btn_pay.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_pay -> PaymentProvider.execute(SERVER_URL, this)
        }
    }

    override fun handlePaymentResult(result: PaymentResult) {
//        progressBar.visibility = View.GONE
        if (result.isSuccess()) {
            Toast.makeText(this, "Payment successful" , Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Payment failed. errorCode = " + result.getErrorCode() + " and cause = " + result.getCause() , Toast.LENGTH_LONG).show()
        }
    }
}
