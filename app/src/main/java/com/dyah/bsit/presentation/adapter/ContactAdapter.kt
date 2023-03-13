package com.dyah.bsit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dyah.bsit.databinding.ItemContactBinding
import com.dyah.bsit.model.ContactResponse

class ContactAdapter(
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindingView(item: ContactResponse) {
            binding.tvUser.text = item.name
            binding.tvMethod.text = item.noTelp
            binding.ivPhone.setOnClickListener {
                onClickPhone.invoke(item)
            }
        }
    }

    private var data: MutableList<ContactResponse> = mutableListOf()
    private  var onClickPhone : (ContactResponse) -> Unit = {}

    fun onClickIconCall(clickContact: (ContactResponse)->Unit){
        onClickPhone = clickContact
    }

    fun setData(newData : MutableList<ContactResponse>){
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ContactViewHolder(
        ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
    )
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindingView(data[position])
    }

    override fun getItemCount(): Int = data.size


}