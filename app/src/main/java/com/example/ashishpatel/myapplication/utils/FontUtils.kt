package com.example.ashishpatel.myapplication.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

/**
 * Created by ashish on 6/2/18.
 */
object FontUtils {

    fun overrideFonts(context: Context, group: ViewGroup) {

        try {
            val font: Typeface
            val count = group.childCount
            var v: View
            //        Log.i(TAG, "overrideRegularFonts: ");
            for (i in 0 until count) {
                v = group.getChildAt(i)
                if (v is TextView) {

                    if (v.typeface != null && v.typeface.isBold) {
                        v.typeface = getBoldFont(context)
                    } /*else if (((TextView) v).getTypeface() != null && ((TextView) v).getTypeface().isItalic()) {
                            Log.d(TAG, "overrideRegularFonts: button");
                            ((TextView) v).setTypeface(getButtonBoldFont(context));
                        }*/
                    else {
                        v.typeface = getNormalFont(context)
                    }

                } else if (v is EditText) {

                    if (v.typeface != null && v.typeface.isBold) {
                        v.typeface = getBoldFont(context)
                    } /*else if (((EditText) v).getTypeface() != null && ((EditText) v).getTypeface().isItalic()) {
                            Log.d(TAG, "overrideRegularFonts: button ");
                            ((EditText) v).setTypeface(getButtonBoldFont(context));
                        }*/
                    else {
                        v.typeface = getNormalFont(context)
                    }

                } else if (v is ViewGroup) setFont(v, context)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun setFont(group: ViewGroup, context: Context) {
        val font: Typeface
        val count = group.childCount
        var v: View
        for (i in 0 until count) {
            v = group.getChildAt(i)
            if (v is TextView) {

                if (v.typeface != null && v.typeface.isBold) {
                    v.typeface = getBoldFont(context)
                } /*else if (((TextView) v).getTypeface() != null && ((TextView) v).getTypeface().isItalic()) {
                        Log.d(TAG, "overrideRegularFonts: button ");
                        ((TextView) v).setTypeface(getButtonBoldFont(context));
                    }*/
                else {
                    v.typeface = getNormalFont(context)
                }

            } else if (v is EditText) {

                if (v.typeface != null && v.typeface.isBold) {
                    v.typeface = getBoldFont(context)
                } /*else if (((EditText) v).getTypeface() != null && ((EditText) v).getTypeface().isItalic()) {
                        Log.d(TAG, "overrideRegularFonts: button ");
                        ((EditText) v).setTypeface(getButtonBoldFont(context));
                    }*/
                else {
                    v.typeface = getNormalFont(context)
                }

            } else if (v is ViewGroup) setFont(v, context)
        }
    }

    private val TAG = "Utility"

    private fun getNormalFont(context: Context): Typeface? {
        val normalFont: Typeface? = null
        if (normalFont == null) {
            //            normalFont = Typeface.createFromAsset(context.getAssets(), Const.REGULAR_FONTS);
            //            Log.i(TAG, "getNormalFont: " + normalFont);
        }
        return normalFont
    }

    /*  private static Typeface getButtonBoldFont(Context context) {
        Typeface normalFont = null;
        if (normalFont == null) {
            normalFont = Typeface.createFromAsset(context.getAssets(), Const.BOLD_FONTS_FOR_BUTTONS);
        }
        return normalFont;
    }*/

    fun getBoldFont(context: Context): Typeface? {
        val boldFont: Typeface? = null
        if (boldFont == null) {

            //            boldFont = Typeface.createFromAsset(context.getAssets(), Const.BOLD_FONTS);
            //            Log.i(TAG, "getBoldFont: " + boldFont);
        }
        return boldFont
    }

}