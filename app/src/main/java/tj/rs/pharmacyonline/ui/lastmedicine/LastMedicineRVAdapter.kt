package tj.rs.pharmacyonline.ui.lastmedicine

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.databinding.RvMedicineItemBinding

/**
 * Created by Rustam Safarov (RS) on 07.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class LastMedicineRVAdapter(
    private var items: ArrayList<Medicine>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<LastMedicineRVAdapter.LastMedicineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMedicineViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvMedicineItemBinding.inflate(layoutInflater, parent, false)
        return LastMedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastMedicineViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        Log.i("LastMedicineRVAdapter", "size:" + items.size)
        return items.size
    }

    fun getItemByPosition(position: Int) = items[position]

    fun replaceData(data: ArrayList<Medicine>) {
        items = data
        Log.i("LastMedicineRVAdapter", "data replaced, new data size: " + data.size)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class LastMedicineViewHolder(private var binding: RvMedicineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(medi: Medicine, listener: OnItemClickListener?) {
            binding.medicine = medi
            if (medi.isFavorite) {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_black_24dp)
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }

            if (listener != null) {
                binding.root.setOnClickListener {
                    listener.onItemClick(layoutPosition)
                }
            }
            binding.executePendingBindings()
        }

    }
}