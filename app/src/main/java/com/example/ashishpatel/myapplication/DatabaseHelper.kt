package com.example.ashishpatel.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DatabaseHelper(private val myContext: Context, filePath: String) : SQLiteOpenHelper(myContext, DATABASE_NAME, null, DATABASE_VERSION) {
    private val pathToSaveDBFile: String

    private val versionId: Int
        get() {
            val db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY)
            val query = "SELECT version_id FROM dbVersion"
            val cursor = db.rawQuery(query, null)
            cursor.moveToFirst()
            val v = cursor.getInt(0)
            db.close()
            Log.i("DatabaseHelper", ": ")
            return v
        }

    init {
        pathToSaveDBFile = StringBuffer(filePath).append("/").append(DATABASE_NAME).toString()
    }

    @Throws(IOException::class)
    fun prepareDatabase() {
        val dbExist = checkDataBase()
        if (dbExist) {
            Log.d(TAG, "Database exists.")
            val currentDBVersion = versionId
            if (DATABASE_VERSION > currentDBVersion) {
                Log.d(TAG, "Database version is higher than old.")
                deleteDb()
                try {
                    copyDataBase()
                } catch (e: IOException) {
                    Log.e(TAG, e.message)
                }

            }
        } else {
            try {
                copyDataBase()
            } catch (e: IOException) {
                Log.e(TAG, e.message)
            }

        }
    }

    private fun checkDataBase(): Boolean {
        var checkDB = false
        try {
            val file = File(pathToSaveDBFile)
            checkDB = file.exists()
        } catch (e: SQLiteException) {
            Log.d(TAG, e.message)
        }

        return checkDB
    }

    @Throws(IOException::class)
    private fun copyDataBase() {
        val os = FileOutputStream(pathToSaveDBFile)
        val `is` = myContext.assets.open("databases/$DATABASE_NAME")
        val buffer = ByteArray(1024)
        var length: Int
        /* while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }*/

        length = `is`.read(buffer)
        while (length > 0) {
            os.write(buffer, 0, length)
            length = `is`.read(buffer)
        }

        `is`.close()
        os.flush()
        os.close()
    }

    fun deleteDb() {
        val file = File(pathToSaveDBFile)
        if (file.exists()) {
            file.delete()
            Log.d(TAG, "Database deleted.")
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private val TAG = "DatabaseHelper"
        private val DATABASE_NAME = "SunehraKal.db"
        private val DATABASE_VERSION = 1
    }
}
