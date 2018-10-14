package com.example.ashishpatel.myapplication

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.widget.ArrayAdapter

class MultiSpinner : android.support.v7.widget.AppCompatSpinner,
        DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnCancelListener {

    private var items: List<String>? = null
    private var selected: BooleanArray? = null
    private var defaultText: String? = null
    private var listener: MultiSpinnerListener? = null

    constructor(context: Context) : super(context) {}

    constructor(arg0: Context, arg1: AttributeSet) : super(arg0, arg1) {}

    constructor(arg0: Context, arg1: AttributeSet, arg2: Int) : super(arg0, arg1, arg2) {}

    override fun onClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        selected!![which] = isChecked
    }

    override fun onCancel(dialog: DialogInterface) {
        // refresh text on spinner
        val spinnerBuffer = StringBuffer()
        var someUnselected = false
        for (i in items!!.indices) {
            if (selected!![i] == true) {
                spinnerBuffer.append(items!![i])
                spinnerBuffer.append(", ")
            } else {
                someUnselected = true
            }
        }
        var spinnerText: String?
        if (someUnselected) {
            spinnerText = spinnerBuffer.toString()
            if (spinnerText.length > 2)
                spinnerText = spinnerText.substring(0, spinnerText.length - 2)
        } else {
            spinnerText = defaultText
        }
        val adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item,
                arrayOf<String>(spinnerText!!))
        setAdapter(adapter)
        listener!!.onItemsSelected(selected!!)
    }

    override fun performClick(): Boolean {
        val builder = AlertDialog.Builder(context)
        builder.setMultiChoiceItems(items!!.toTypedArray<CharSequence>(), selected, this)
        builder.setPositiveButton(android.R.string.ok
        ) { dialog, which -> dialog.cancel() }
        builder.setOnCancelListener(this)
        builder.show()
        return true
    }

    fun setItems(items: List<String>, allText: String,
                 listener: MultiSpinnerListener) {
        this.items = items
        this.defaultText = allText
        this.listener = listener

        // all selected by default
        selected = BooleanArray(items.size)
        for (i in selected!!.indices)
            selected!![i] = true

        // all text on the spinner
        val adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item, arrayOf(allText))
        setAdapter(adapter)
    }

    interface MultiSpinnerListener {
        fun onItemsSelected(selected: BooleanArray)
    }
}
