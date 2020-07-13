package tj.rs.pharmacyonline.ui.lastmedicine

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.databinding.RvMedicineItemBinding

/**
 * Created by Rustam Safarov (RS) on 07.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class LastMedicineRVAdapter(
    private var items: ArrayList<Medicine>,
    private var listener: OnItemClickListener,
    private var favoriteItemClickCallback: OnFavoriteItemClickCallback

) : RecyclerView.Adapter<LastMedicineRVAdapter.LastMedicineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMedicineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvMedicineItemBinding.inflate(layoutInflater, parent, false)
        return LastMedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastMedicineViewHolder, position: Int) {
        holder.bind(items[position], listener, favoriteItemClickCallback)
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

    interface OnFavoriteItemClickCallback {
        fun onClick(medicine: Medicine)
    }

    inner class LastMedicineViewHolder(private var binding: RvMedicineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            medi: Medicine,
            listener: OnItemClickListener?,
            favoriteItemClickCallback: OnFavoriteItemClickCallback
        ) {
            binding.medicine = medi
            if (listener != null) {
                binding.root.setOnClickListener {
                    listener.onItemClick(layoutPosition)
                }
            }
            if (favoriteItemClickCallback != null) {
                binding.ivFavorite.setOnClickListener {
                    favoriteItemClickCallback.onClick(medi)
                }
            }
            binding.executePendingBindings()
        }

    }
}