package com.example.ashishpatel.myapplication.utils

import android.graphics.*


/**
 * Created by Ashish on 10/2/18.
 */
object BitmapUtils {

    fun getRoundedCornerBitmap(bm: Bitmap, pixels: Int): Bitmap {
        val output = Bitmap.createBitmap(bm.width, bm.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bm.width, bm.height)
        val rectF = RectF(rect)
        val roundPx = pixels.toFloat()



        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bm, rect, rect, paint)
        return output
    }

}