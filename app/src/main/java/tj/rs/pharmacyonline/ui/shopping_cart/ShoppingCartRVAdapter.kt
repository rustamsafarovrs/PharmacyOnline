package tj.rs.pharmacyonline.ui.shopping_cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.data.model.MedicineCart
import tj.rs.pharmacyonline.databinding.RvMedicineCartItemBinding

/**
 * Created by Rustam Safarov (RS) on 07.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ShoppingCartRVAdapter(
    private var items: ArrayList<MedicineCart>
) : RecyclerView.Adapter<ShoppingCartRVAdapter.ShoppingCartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvMedicineCartItemBinding.inflate(layoutInflater, parent, false)
        return ShoppingCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        Log.i("LastMedicineRVAdapter", "size:" + items.size)
        return items.size
    }

    fun submitList(list: ArrayList<MedicineCart>) {
        items = list
        notifyDataSetChanged()
    }

    inner class ShoppingCartViewHolder(private var binding: RvMedicineCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            medicineCart: MedicineCart
        ) {
            binding.medicine = medicineCart

            binding.executePendingBindings()
        }

    }
}