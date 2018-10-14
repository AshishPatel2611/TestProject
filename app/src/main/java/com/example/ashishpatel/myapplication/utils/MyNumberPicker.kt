package com.example.ashishpatel.myapplication.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import com.example.ashishpatel.myapplication.R


class MyNumberPicker : NumberPicker {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun addView(child: View) {
        super.addView(child)
        updateView(child)
    }

    override fun addView(child: View, index: Int, params: android.view.ViewGroup.LayoutParams) {
        super.addView(child, index, params)
        updateView(child)
    }

    override fun addView(child: View, params: android.view.ViewGroup.LayoutParams) {
        super.addView(child, params)
        updateView(child)
    }

    private fun updateView(view: View) {
        if (view is EditText) {
            // view.typeface = ResourcesCompat.getFont(view.getContext(), FONT_ID)
            view.setTextColor(ContextCompat.getColor(view.context, R.color.colorPrimaryDark))
          //  view.typeface = ResourcesCompat.getFont(view.context, R.font.lato_bold)
            view.isEnabled = false
            view.isFocusable = false

            Log.i("MyNumberPicker","updateView : ${view.text} ")

        }

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
            val pickerFields = NumberPicker::class.java.declaredFields
            try {
                for (pf in pickerFields) {
                    if (pf.name.equals("mSelectionDivider")) {

                        pf.isAccessible = true
                        try {
                            val colorDrawable = ColorDrawable(ContextCompat.getColor(context, R.color.primary_dark_material_dark))
                            pf.set(this, colorDrawable)
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        } catch (e: Resources.NotFoundException) {
                            e.printStackTrace()
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        }
                        break
                    }
                }
            } catch (e: Exception) {
                Log.e("MyNumberPicker", "updateView : ${e.printStackTrace()} ")
            }
        }
        //   Log.i("MyNumberPicker","updateView : $view ")

    }

}
