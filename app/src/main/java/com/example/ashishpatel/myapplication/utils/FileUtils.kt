package com.example.ashishpatel.myapplication.utils

import android.os.Build
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by ashish on 6/2/18.
 */
object FileUtils {

    @Throws(IOException::class)
    fun copyFile(src: File, dst: File) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            FileInputStream(src).use { value ->
                FileOutputStream(dst).use { out ->
                    // Transfer bytes from in to out
                    val buf = ByteArray(1024)
                    var len = value.read(buf)
                    while (len > 0) {
                        out.write(buf, 0, len)
                        len = value.read(buf)
                    }
                }
            }
        } else {
            val value = FileInputStream(src)
            try {
                val out = FileOutputStream(dst)
                try {
                    // Transfer bytes from in to out
                    val buf = ByteArray(1024)
                    var len = value.read(buf)
                    while (len > 0) {
                        out.write(buf, 0, len)
                        len = value.read(buf)
                    }
                } finally {
                    out.close()
                }
            } finally {
                value.close()
            }
        }
    }



}