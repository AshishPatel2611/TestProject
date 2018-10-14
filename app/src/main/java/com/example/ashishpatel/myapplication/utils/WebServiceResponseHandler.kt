package com.example.ashishpatel.myapplication.utils

import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Created by ashish on 9/2/18.
 */
object WebServiceResponseHandler {

    interface DataHandlerWithLimit {
        fun onSuccess(data: JsonElement, message: String, limit: Limit)
        fun onFailure(data: String)
        fun noInternetConnection()

    }

    interface DataHandler {
        fun onSuccess(data: JsonElement, message: String)
        fun onFailure(data: String)
        fun noInternetConnection()

    }


    fun handleApiResponse(apiCall: Call<ResponseBody>, dataHandlerWithLimit: DataHandlerWithLimit): Call<ResponseBody> {

        apiCall.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                if (call!!.isCanceled) {
                    Log.e("WebServiceResponseHndlr", "onFailure : call.isCanceled : " + call.isCanceled)
                } else if (t is IOException && t.message.equals(Const.NO_INTERNET, true)) {
                    dataHandlerWithLimit.noInternetConnection()
                } else {
                    dataHandlerWithLimit.onFailure("Internal Server error") //
                }

                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

                if (response!!.body() != null) {
                    if (response.body()!!.status == 200) { //success

                        dataHandlerWithLimit.onSuccess(response.body()!!.data!!, response.body()!!.message, response.body()!!.limit)

                    } else if (response.body()!!.status == 401) {// wrongToken //No Token Send
                        dataHandlerWithLimit.onFailure(response.body()!!.message)
                    } else if (response.body()!!.status == 400) { // wrong data send
                        dataHandlerWithLimit.onFailure(response.body()!!.message)
                    } else if (response.body()!!.status == 404) { // result not found
                        dataHandlerWithLimit.onFailure(response.body()!!.message)
                    } else if (response.body()!!.status == 405) { //Invalid Request Method
                        dataHandlerWithLimit.onFailure(response.body()!!.message)
                    } else {
                        dataHandlerWithLimit.onFailure(response.body()!!.message)
                    }
                } else {
                    dataHandlerWithLimit.onFailure("Internal Server error") // retrofit error
                }
            }
        })
        return apiCall
    }


    fun handleApiResponse(apiCall: Call<ResponseBody>, dataHandler: DataHandler): Call<ResponseBody> {

        apiCall.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

                if (call!!.isCanceled) {
                    Log.e("WebServiceResponseHndlr", "onFailure : call.isCanceled : " + call.isCanceled)
                } else if (t is IOException && t.message.equals(Const.NO_INTERNET, true)) {
                    dataHandler.noInternetConnection()
                } else {
                    dataHandler.onFailure("Internal Server error") //
                }

                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

                if (response!!.body() != null) {
                    if (response.body()!!.status == 200) { //success

                        dataHandler.onSuccess(response.body()!!.data!!, response.body()!!.message)

                    } else if (response.body()!!.status == 401) {// wrongToken //No Token Send
                        dataHandler.onFailure(response.body()!!.message)
                    } else if (response.body()!!.status == 400) { // wrong data send
                        dataHandler.onFailure(response.body()!!.message)
                    } else if (response.body()!!.status == 404) { // result not found
                        dataHandler.onFailure(response.body()!!.message)
                    } else if (response.body()!!.status == 405) { //Invalid Request Method
                        dataHandler.onFailure(response.body()!!.message)
                    } else {
                        dataHandler.onFailure(response.body()!!.message)
                    }
                } else {
                    dataHandler.onFailure("Internal Server error") // retrofit error
                }
            }
        })

        return apiCall

    }

    class ResponseBody {
        @SerializedName("status")
        val status: Int? = null
        @SerializedName("message")
        val message: String = ""
        @SerializedName("data")
        val data: JsonElement? = null
        @SerializedName("limit")
        val limit: Limit = Limit()
    }

    class Limit {
        @SerializedName("TotalRecord")
        val TotalRecord: String = ""
        @SerializedName("limit")
        val limit: String = ""
        @SerializedName("continue")
        val isContinue: String = ""
    }

}