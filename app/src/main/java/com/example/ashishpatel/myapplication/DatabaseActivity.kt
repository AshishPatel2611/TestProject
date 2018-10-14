package com.example.ashishpatel.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.IOException


class DatabaseActivity : AppCompatActivity() {

    var dbHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_database)

        dbHelper = DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper!!.prepareDatabase()
        } catch (e: IOException) {
            Log.e("tag", e.message)
        }

    }


}
