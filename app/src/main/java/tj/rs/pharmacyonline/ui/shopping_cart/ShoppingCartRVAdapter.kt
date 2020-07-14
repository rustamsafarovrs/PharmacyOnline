package tj.rs.pharmacyonline.ui.shopping_cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.data.model.MedicineCart
import tj.rs.pharmacyonline.databinding.RvMedicineCartItemBinding
import tj.rs.pharmacyonline.databinding.RvShoppingCartFooterBinding
import tj.rs.pharmacyonline.ui_commons.RecyclerViewItemClickCallback

/**
 * Created by Rustam Safarov (RS) on 07.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ShoppingCartRVAdapter(
    private var items: ArrayList<MedicineCart>,
    var listener: RecyclerViewItemClickCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_FOOTER: Int = 0
    private val VIEW_TYPE_CELL: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_CELL -> {
                val binding = RvMedicineCartItemBinding.inflate(layoutInflater, parent, false)
                ShoppingCartViewHolder(binding)
            }
            VIEW_TYPE_FOOTER -> {
                val binding = RvShoppingCartFooterBinding.inflate(layoutInflater, parent, false)
                ShoppingCartFooterViewHolder(binding)
            }
            else -> {
                throw IllegalStateException("Incorrect ViewType found")
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size) VIEW_TYPE_FOOTER else VIEW_TYPE_CELL
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_CELL -> {
                var viewHolder = holder as ShoppingCartViewHolder
                viewHolder.bind(items[position], listener)
            }
            VIEW_TYPE_FOOTER -> {
                var viewHolder = holder as ShoppingCartFooterViewHolder
                viewHolder.bind()
            }
        }
    }

    override fun getItemCount(): Int {
        Log.i("LastMedicineRVAdapter", "size:" + items.size)
        var count = items.size
        return ++count
    }

    fun submitList(list: ArrayList<MedicineCart>) {
        items = list
        notifyDataSetChanged()
    }

    inner class ShoppingCartViewHolder(private var binding: RvMedicineCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            medicineCart: MedicineCart,
            listener: RecyclerViewItemClickCallback
        ) {
            binding.medicine = medicineCart
            binding.listener = listener
            binding.executePendingBindings()
        }

    }

    inner class ShoppingCartFooterViewHolder(private var binding: RvShoppingCartFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.executePendingBindings()
        }

    }
}