package com.example.ashishpatel.myapplication.utils

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by ashish on 9/2/18.
 */
object RetrofitUtil {

    interface DataHandlerWithLimit {
        fun onSuccess(data: JsonElement, message: String, limit: Limit)
        fun onFailure(data: String)
        fun noInternetConnection()
        fun loginRequired()
    }

    interface DataHandler {
        fun onSuccess(data: JsonElement, message: String)
        fun onFailure(data: String)
        fun noInternetConnection()
        fun loginRequired()
    }

    fun handleApiResponse(apiCall: Call<APIresponse>, dataHandlerWithLimit: DataHandlerWithLimit) {

        apiCall.enqueue(object : Callback<APIresponse> {

            override fun onFailure(call: Call<APIresponse>?, t: Throwable?) {
                dataHandlerWithLimit.onFailure("Internal Server error") //
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<APIresponse>?, response: Response<APIresponse>?) {

                if (response!!.body() != null) {
                    if (response.body()!!.status == 200) { //success
                        dataHandlerWithLimit.onSuccess(response.body()!!.data!!, response.body()!!.message, response.body()!!.limit)
                    } else if (response.body()!!.status == 401) {// wrongToken //No Token Send
                        dataHandlerWithLimit.loginRequired()
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
    }

    fun handleApiResponse(apiCall: Call<APIresponse>, dataHandlerWithLimit: DataHandler): Call<APIresponse> {

        apiCall.enqueue(object : Callback<APIresponse> {

            override fun onFailure(call: Call<APIresponse>?, t: Throwable?) {
                dataHandlerWithLimit.onFailure("Internal Server error") //
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<APIresponse>?, response: Response<APIresponse>?) {

                if (response!!.body() != null) {
                    if (response.body()!!.status == 200) { //success
                        dataHandlerWithLimit.onSuccess(response.body()!!.data!!, response.body()!!.message)
                    } else if (response.body()!!.status == 401) {// wrongToken //No Token Send
                        dataHandlerWithLimit.loginRequired()
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

    class APIresponse {
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