package com.dmm.checkinstudio.ViewAdapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dmm.checkinstudio.R
import com.dmm.checkinstudio.databinding.ListItemCkeckinBinding
import com.dmm.checkinstudio.databinding.UserItemBinding
import com.dmm.checkinstudio.entities.CheckIn

class ReportsAdapter : RecyclerView.Adapter<ReportsAdapter.MyViewHolder>(){

    inner class  MyViewHolder(val itemUserBinding: UserItemBinding): RecyclerView.ViewHolder(
        itemUserBinding.root
    ){

    }

    private  val differCallback = object : DiffUtil.ItemCallback<CheckIn>(){
        override fun areItemsTheSame(oldItem: CheckIn, newItem: CheckIn): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CheckIn, newItem: CheckIn): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(differ.currentList[position]){
                itemUserBinding.textToken.text = token
                itemUserBinding.textDatahora.text = datahora
            }
        }
    }

}
