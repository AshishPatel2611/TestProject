package com.example.ashishpatel.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    val listOfGuest = arrayListOf<GuestModel>(GuestModel("", ""))
    val onGuestListUpdateListner = object : GuestListAdapter.OnGuestListUpdateListner {
        override fun onGuestAdded() {
            listOfGuest.add(GuestModel("", ""))
            rcy.adapter = null
            rcy.adapter = GuestListAdapter(this@Main2Activity, listOfGuest, this)
        }

        override fun onGuestRemoved(p1: GuestModel) {
            listOfGuest.remove(p1)
            rcy.adapter = null
            rcy.adapter = GuestListAdapter(this@Main2Activity, listOfGuest, this)
        }

        override fun onGuestUpdated(listOfGuest: ArrayList<GuestModel>) {
            //  Log.i("UPDATE", "" + listOfGuest.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rcy.adapter = GuestListAdapter(this@Main2Activity, listOfGuest, onGuestListUpdateListner)


        btn.setOnClickListener {
            Log.i("BTN", "" + listOfGuest.toString())
        }
    }
}
