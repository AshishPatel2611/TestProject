package com.example.ashishpatel.myapplication

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.raw_item.view.*

class GuestListAdapter(main2Activity: Main2Activity,
                       val listOfGuest: ArrayList<GuestModel>,
                       val onGuestListUpdateListner: OnGuestListUpdateListner)
    : RecyclerView.Adapter<GuestListAdapter.GuestViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GuestViewHolder {

        return GuestViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.raw_item, null))
    }

    override fun getItemCount(): Int {
        return listOfGuest.size
    }

    override fun onBindViewHolder(p0: GuestViewHolder, p1: Int) {

        p0.itemView.name.setText(listOfGuest[p1].name)
        p0.itemView.desi.setText(listOfGuest[p1].desi)

        if (p1 == listOfGuest.size - 1) {
            p0.itemView.btnRemove.visibility = View.GONE
            p0.itemView.btnAdd.visibility = View.VISIBLE
            p0.itemView.btnAdd.setOnClickListener {
                onGuestListUpdateListner.onGuestAdded()
            }
            p0.itemView.name.requestFocus()
        } else {
            p0.itemView.btnRemove.visibility = View.VISIBLE
            p0.itemView.btnAdd.visibility = View.GONE
            p0.itemView.btnRemove.setOnClickListener {
                onGuestListUpdateListner.onGuestRemoved(listOfGuest[p1])
            }
        }

        p0.itemView.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                    listOfGuest[p1].name = s.toString().trim()
                onGuestListUpdateListner.onGuestUpdated(listOfGuest)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        p0.itemView.desi.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                listOfGuest[p1].desi = s.toString().trim()
                    onGuestListUpdateListner.onGuestUpdated(listOfGuest)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }


    class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface OnGuestListUpdateListner {
        fun onGuestAdded()
        fun onGuestRemoved(p1: GuestModel)
        fun onGuestUpdated(listOfGuest: ArrayList<GuestModel>)
    }


}
