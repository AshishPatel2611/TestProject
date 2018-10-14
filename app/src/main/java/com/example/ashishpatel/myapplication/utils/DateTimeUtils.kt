package com.example.ashishpatel.myapplication.utils

import android.annotation.SuppressLint
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by codexalters on 6/2/18.
 */
object DateTimeUtils {

  private fun convert_UTC_TO_LOCAL_TZ_Date(srcDateString :String) :String {

        // here we will set srcDate Timezone as UTC-GMT
        // & convert into device default time zone

        var resultDateString = srcDateString;

        try {
            @SuppressLint("SimpleDateFormat") val srcDf =  SimpleDateFormat("dd MMM yyyy hh:mm:ss");
            srcDf.timeZone = TimeZone.getTimeZone("GMT");

            // parse the date string into Date object
            val srcDate = srcDf.parse(srcDateString);

            @SuppressLint("SimpleDateFormat") val destDf =  SimpleDateFormat("dd MMM yyyy hh:mm:ss");
            destDf.timeZone = TimeZone.getDefault();

            // format the date into another format

            Log.w("util", "convert_UTC_TO_LOCAL_TZ_Date before  : " + srcDf.getTimeZone().getDisplayName() + " > " + srcDateString);
            resultDateString = destDf.format(srcDate)
            Log.d("Util", "convert_UTC_TO_LOCAL_TZ_Date after : " + destDf.getTimeZone().getDisplayName() + " > " + resultDateString);

        } catch (e:ParseException) {
            e.printStackTrace();
        }
        return resultDateString;
    }


    fun isValidSecondTime(dateOne: Date, dateTwo: Date): Boolean {

        val millis = dateOne.time - dateTwo.time
        val hours = millis / (1000 * 60 * 60)
        val mins = millis % (1000 * 60 * 60)

        Log.i("DateTimeUtils", "getDateDifferenceInBoolean : $ " + hours + ":" + mins)
        return hours < 0 || mins < 0
    }

    fun getDateDiffInDays(dateOne: Date, dateTwo: Date): String {

        val timeOne = dateOne.time
        val timeTwo = dateTwo.time
        val oneDay = (1000 * 60 * 60 * 24).toLong()
        var delta = (timeTwo - timeOne) / oneDay

        if (delta > 0) {
            return (delta + 1).toString() + " Days"
        } else {
            delta *= -1
            return "-$delta Days"
        }
    }

    fun getDateDifference(sessionStart: Date?, sessionEnd: Date?): String {
        if (sessionStart == null || sessionEnd == null)
            return ""

        val startDateTime = Calendar.getInstance()
        startDateTime.time = sessionStart

        val endDateTime = Calendar.getInstance()
        endDateTime.time = sessionEnd

        val milliseconds1 = startDateTime.timeInMillis
        val milliseconds2 = endDateTime.timeInMillis
        val diff = milliseconds2 - milliseconds1

        val hours = diff / (60 * 60 * 1000)
        var minutes = diff / (60 * 1000)
        minutes -= 60 * hours
        val seconds = diff / 1000

        return if (hours > 0) {
            hours.toString() + " hours " + minutes + " minutes"
        } else {
            if (minutes > 0)
                minutes.toString() + " minutes"
            else {
                seconds.toString() + " seconds"
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getStringFromDate(dateFormat: String, date: Date): String? {
        var newString: String? = null
        try {
            var newFormat: SimpleDateFormat? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                newFormat = SimpleDateFormat(dateFormat)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                newString = newFormat!!.format(date)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return newString
    }


    @SuppressLint("SimpleDateFormat")
    fun getDateFromString(dateString: String): Date {

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        try {
            return format.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            return Date()
        }
    }


    fun pad(value: Int): String {
        val h = if (value < 10) {
            "0$value"
        } else {
            value.toString()
        }
        return h
    }

    // timeStamp to data
    fun getDate(time: Long, format: String): String {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time
        return DateFormat.format(format, cal).toString()
    }

    fun getTimeZone(): String {
        return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
    }

    fun getTimeInStringFormat(format: String): String {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        return DateFormat.format(format, cal).toString()
    }




    @SuppressLint("SimpleDateFormat")
    fun convertDateFormat(dateString: String, srcDateFormat: String, dstDateFormat: String): String {
        var newString: String = ""
        val format = SimpleDateFormat(srcDateFormat)
        try {
            val newFormat = SimpleDateFormat(dstDateFormat, Locale.ENGLISH)
            newString = newFormat.format(format.parse(dateString))
        } catch (e: ParseException) {
            e.printStackTrace()

        }
        return newString
    }




    fun splitToComponentTime(seconds: Int): Array<String> {

        val hours = seconds / 3600
        var remainder = seconds - hours * 3600
        val mins = remainder / 60
        remainder -= mins * 60
        val secs = remainder

        return arrayOf<String>(pad(hours), pad(mins), pad(secs))
    }
}
