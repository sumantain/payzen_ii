package com.sbw.wish.paymentact.pay

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.system.ErrnoException
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

/**
 * Util class to manage merchant server call and payment result construction
 */
object PaymentProvider {
    // Technical code used for specifying the result of WebView activity
    const val WEBVIEW_ACTIVITY_CODE_RESULT = 981

    /**
     * HTTP request to merchant server
     *
     * @param payload PaymentData
     * @param serverUrl String
     * @param activity Activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    fun execute(payload: PaymentData, serverUrl: String, activity: Activity) {
        fun execute(serverUrl: String, activity: Activity) {
        doAsync {
            try {
//                val conn = URL(serverUrl).openConnection() as HttpURLConnection
//                conn.requestMethod = "POST"
//                conn.setRequestProperty("Content-type", "application/json")
//                conn.setRequestProperty("Accept", "*/*")
//                conn.doInput = true
//                conn.doOutput = true
//                conn.connectTimeout = 15000
//
//                val os = conn.outputStream
//                val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
//                writer.write(payload.toString())
//                writer.flush()
//                writer.close()
//                os.close()
//
//                conn.connect()
//
//                val out = OutputStreamWriter(conn.outputStream)
//
//
//                if (conn.responseCode in 200..299) {
//                    // If response is OK
//                    val content = BufferedReader(InputStreamReader(conn.inputStream)).use(
//                        BufferedReader::readText)

                    val intent = Intent(activity, PaymentActivity::class.java)
                    intent.putExtra("redirectionUrl", serverUrl)
                    intent.putExtra("activityCodeResult", WEBVIEW_ACTIVITY_CODE_RESULT)
                    activity.startActivityForResult(intent, WEBVIEW_ACTIVITY_CODE_RESULT)
//                } else {
//                    // If response is KO
//                    val content = BufferedReader(InputStreamReader(conn.errorStream)).use(
//                        BufferedReader::readText)
//                    activity.runOnUiThread {
//                        try {
//                            returnsResult(false, PaymentErrorCode.SERVER_ERROR, conn.responseCode.toString() + ":" + JSONObject(content).get("errorMessage"), activity)
//                        } catch (e: JSONException) {
//                            returnsResult(false, PaymentErrorCode.SERVER_ERROR, conn.responseCode.toString(), activity)
//                        }
//                    }
//                }
//
//                out.close()
            } catch (e: SocketTimeoutException) {
                activity.runOnUiThread {
                    returnsResult(false, PaymentErrorCode.TIMEOUT_ERROR, "Timeout error", activity)
                }
            } catch (e: ErrnoException) {
                activity.runOnUiThread {
                    returnsResult(false, PaymentErrorCode.NO_CONNECTION_ERROR, "No connection error", activity)
                }
            } catch (e: IOException) {
                activity.runOnUiThread {
                    returnsResult(false, PaymentErrorCode.UNKNOWN_ERROR, e.message, activity)
                }
            }

        }.execute()
    }

    /**
     * Returns payment result to main activity
     *
     * @param value Boolean
     * @param errorCode Int?
     * @param cause String?
     */
    fun returnsResult(value: Boolean, errorCode: Int?, cause: String?, activity: Activity) {
        (activity as AbstractPaymentActivity).handlePaymentResult(constructPaymentResult(value, errorCode, cause))
    }

    /**
     * Construct JSONObject from result parts
     *
     * @param value Boolean
     * @param errorCode Int?
     * @param cause String?
     * @return JSONObject
     */
    private fun constructPaymentResult(value: Boolean, errorCode: Int?, cause: String?): PaymentResult {
        val result = PaymentResult()
        result.setSuccess(value)
        if (!value) {
            result.setErrorCode(errorCode)
            result.setCause(cause)
        }

        return result
    }

    /**
     * Allow to execute HTTP call outside UI thread
     * @property handler Function0<Unit>
     * @constructor
     */
    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
}