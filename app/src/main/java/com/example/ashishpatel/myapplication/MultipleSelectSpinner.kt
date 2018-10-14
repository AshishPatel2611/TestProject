package com.example.ashishpatel.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_multiple_select_spinner.*
import java.util.*

class MultipleSelectSpinner : AppCompatActivity(), MultiSpinner.MultiSpinnerListener {

    override fun onItemsSelected(selected: BooleanArray) {
        Log.i("0", "selected :0=" + Arrays.toString(selected))
    }


    val arrayOfObject = arrayListOf<GuestModel>(GuestModel("Select guest", ""))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_select_spinner)

        for (i in 1..25) {
            arrayOfObject.add(GuestModel("i =$i", "sfsf"))
        }

        val arry = arrayListOf<String>()
        for (i in 0 until arrayOfObject.size) {
            arry.add("i =$i")
        }
        multispinner.setItems(arry, "Select Villages", this)

    }


    fun a(a: String) {
        Log.e("MultipleSelectSpinner", "a: ")
    }
}
