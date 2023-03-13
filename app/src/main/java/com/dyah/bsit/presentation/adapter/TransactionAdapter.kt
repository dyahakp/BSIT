package com.dyah.bsit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dyah.bsit.R
import com.dyah.bsit.databinding.ItemTransactionBinding
import com.dyah.bsit.model.TransactionResponse

//1
class TransactionAdapter(
    private val data: List<TransactionResponse>
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = TransactionViewHolder(
        ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bindingView(data[position])
    }

    inner class TransactionViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindingView(item: TransactionResponse) {
            binding.tvUser.text = item.name
            binding.tvMethod.text = item.metodeTrf
            if (item.flagDebitCredit == 1) {
                binding.tvNominal.setTextColor(
                    binding.root.context.resources.getColor(R.color.red)
                )
            } else {
                binding.tvNominal.setTextColor(
                    binding.root.context.resources.getColor(R.color.green)
                )
            }
            binding.tvNominal.text = item.nominalSaldo.toString()
            Glide
                .with(binding.root.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(binding.ivUser)

        }

    }

}