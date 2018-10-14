package com.example.ashishpatel.myapplication.utils

import android.content.Context
import com.example.ashishpatel.myapplication.utils.MyAppPreferenceUtils.getToken
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitWebServiceUtils {

    lateinit var webService: WebServices
    private var apiCall: retrofit2.Call<WebServiceResponseHandler.ResponseBody>? = null

    const val HEADER_ContentType = "Content-Type"
    const val HEADER_vAuthToken = "vAuthToken"
    const val CONTENT_TYPE_FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    const val CONTENT_TYPE_FORM_DATA = "form-data"

    fun init(context: Context) {
        val gson = GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                .create()

        val vAuthToken = getToken(context)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val internetAccessInterceptor = Interceptor { chain ->
            //this will check internet connection availability every time request made by user
            if (ConnectionUtil.isDataConnectionAvailable(context)) {
                return@Interceptor chain.proceed(chain.request())
            } else {
                throw IOException(Const.NO_INTERNET)
            }
        }

        val headerInterceptor = Interceptor { chain ->
            //this will add required headers to APIs
            return@Interceptor chain.proceed(chain.request().newBuilder()
                    //   .addHeader(HEADER_ContentType, CONTENT_TYPE_FORM_URL_ENCODED)
                    .addHeader(HEADER_vAuthToken, vAuthToken)
                    .build())
        }

        val okHttpClient =
                OkHttpClient.Builder()
                        .addInterceptor(internetAccessInterceptor)
                        .addInterceptor(httpLoggingInterceptor)
                        //    .addInterceptor(headerInterceptor)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(1, TimeUnit.MINUTES)
                        .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(WebServices.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        webService = retrofit!!.create(WebServices::class.java)
    }

    fun callSoundData(lastUpdatedDate: String, dataHandler: WebServiceResponseHandler.DataHandler):
            retrofit2.Call<WebServiceResponseHandler.ResponseBody> {

        if (apiCall != null) {
            apiCall!!.cancel()
        }
        apiCall = webService.getSoundData(lastUpdatedDate)

        return WebServiceResponseHandler.handleApiResponse(
                apiCall!!,
                dataHandler)
    }

    fun callSoundGuideData(dataHandler: WebServiceResponseHandler.DataHandler): retrofit2.Call<WebServiceResponseHandler.ResponseBody> {

        if (apiCall != null) {
            apiCall!!.cancel()
        }

        apiCall = webService.getSoundGuideData()

        return WebServiceResponseHandler.handleApiResponse(
                apiCall!!,
                dataHandler)
    }

    fun callTerms(dataHandler: WebServiceResponseHandler.DataHandler): retrofit2.Call<WebServiceResponseHandler.ResponseBody> {
        if (apiCall != null) {
            apiCall!!.cancel()
        }

        apiCall = webService.getTerms()

        return WebServiceResponseHandler.handleApiResponse(
                apiCall!!,
                dataHandler)
    }


    interface WebServices {

        companion object {

            //  const val BASE_URL = "http://192.168.1.59/arthur/ws/User/"
            const val BASE_URL = "http://codexalters-techlabs.com/arthur/ws/User/"
            const val SOUND_LIST = "getSoundDetailsList"
            const val SOUND_GUIDE_LIST = "getSoundGuidesList"
            const val TERMS = "getTerms"
        }


        @FormUrlEncoded
        @POST(SOUND_LIST)
        fun getSoundData(@Field("lastUpdatedDate") lastUpdatedDate: String): Call<WebServiceResponseHandler.ResponseBody>

        @GET(SOUND_GUIDE_LIST)
        fun getSoundGuideData(): Call<WebServiceResponseHandler.ResponseBody>

        @GET(TERMS)
        fun getTerms(): Call<WebServiceResponseHandler.ResponseBody>

    }
}