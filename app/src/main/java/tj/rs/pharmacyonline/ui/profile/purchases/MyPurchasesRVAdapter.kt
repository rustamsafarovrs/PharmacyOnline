package tj.rs.pharmacyonline.ui.profile.purchases

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.databinding.RvMedicineItemBinding

/**
 * Created by Rustam Safarov (RS) on 17.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class MyPurchasesRVAdapter(
    private var items: ArrayList<Medicine>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyPurchasesRVAdapter.MyPurchasesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPurchasesViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvMedicineItemBinding.inflate(layoutInflater, parent, false)
        return MyPurchasesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyPurchasesViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    fun replaceData(data: ArrayList<Medicine>) {
        items = data
        Log.i("MyPurchasesRVAdapter", "data replaced, new data size: " + data.size)

    }

    inner class MyPurchasesViewHolder(private val binding: RvMedicineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(medi: Medicine, listener: OnItemClickListener?) {
            binding.medicine = medi
            if (listener != null) {
                binding.root.setOnClickListener {
                    listener.onItemClick(layoutPosition)
                }
            }
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
