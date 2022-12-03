package com.dmm.checkinstudio.ViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dmm.checkinstudio.R
import com.dmm.checkinstudio.databinding.ListItemCkeckinBinding
import com.dmm.checkinstudio.entities.CheckIn

class MyRecyclerViewAdapter(private val clickListener: (CheckIn) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    private val subscribersList = ArrayList<CheckIn>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemCkeckinBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_ckeckin, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position], clickListener)
    }

    fun setList(subscribers: List<CheckIn>) {
        subscribersList.clear()
        subscribersList.addAll(subscribers)

    }

}

class MyViewHolder(val binding: ListItemCkeckinBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(checkIn: CheckIn, clickListener: (CheckIn) -> Unit) {
        binding.nameTextView.text = checkIn.token
        binding.emailTextView.text = checkIn.biometria
        binding.listItemLayout.setOnClickListener {
            clickListener(checkIn)
        }
    }
}