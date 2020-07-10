package tj.rs.pharmacyonline.ui.medicinedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.data.model.Price
import tj.rs.pharmacyonline.databinding.RvPriceItemBinding
import tj.rs.pharmacyonline.ui_commons.RecyclerViewItemClickCallback

/**
 * Created by Rustam Safarov (RS) on 10.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class PriceRVAdapter(val listener: RecyclerViewItemClickCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is Price && newItem is Price -> {
                    oldItem.price == newItem.price
                }
                else -> {
                    false
                }
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is Price && newItem is Price -> {
                    oldItem as Price == newItem as Price
                }
                else -> {
                    false
                }
            }
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: RvPriceItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.rv_price_item, parent, false)

        return PriceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Any>) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: PriceViewHolder = holder as PriceViewHolder
        viewHolder.bind(differ.currentList[position] as Price)
        if (position == 0) {
            holder.bestPrice()
        }
    }

    inner class PriceViewHolder(private val binding: RvPriceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(price: Price) {
            binding.tvDepartmentAddress.isSelected = true
            binding.price = price
            binding.recyclerViewItemClickCallback = listener
            binding.executePendingBindings()
        }

        fun bestPrice() {
            binding.ivBestPrice.visibility = View.VISIBLE
        }

    }
}