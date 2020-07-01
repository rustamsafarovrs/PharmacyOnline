package tj.rs.pharmacyonline.ui.catalogs.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.data.model.CatalogPhoneCountry
import tj.rs.pharmacyonline.databinding.AdapterSelectCatalogCodeBinding
import tj.rs.pharmacyonline.ui_commons.RecyclerViewItemClickCallback

/**
 * Created by Rustam Safarov (RS) on 30.06.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class SelectCatalogAdapter(var recyclerViewItemClickCallback: RecyclerViewItemClickCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_PHONE_COUNTRY = 0
    private val VIEW_TYPE_TITLE_VALUE = 1

    private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is CatalogPhoneCountry && newItem is CatalogPhoneCountry -> {
                    oldItem.code == newItem.code
                }
                else -> {
                    false
                }
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is CatalogPhoneCountry && newItem is CatalogPhoneCountry -> {
                    oldItem as CatalogPhoneCountry == newItem as CatalogPhoneCountry
                }
                else -> {
                    false
                }
            }
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Any>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_PHONE_COUNTRY -> {
                val binding: AdapterSelectCatalogCodeBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.adapter_select_catalog_code,
                    parent,
                    false
                )

                PhoneCountryViewHolder(binding)
            }
//            VIEW_TYPE_TITLE_VALUE -> {
//                val binding: AdapterSelectCatalogTitleValueBinding =
//                    DataBindingUtil.inflate(inflater, R.layout.adapter_select_catalog_title_value, parent, false)
//                TitleValueViewHolder(binding)
//            }

            else -> {
                throw IllegalStateException("Incorrect ViewType found")
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_PHONE_COUNTRY -> {
                var viewHolder = holder as PhoneCountryViewHolder
                viewHolder.initContent(differ.currentList[position] as CatalogPhoneCountry)
            }
            // coming soon

        }
    }

    inner class PhoneCountryViewHolder(var binding: AdapterSelectCatalogCodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initContent(item: CatalogPhoneCountry) {
            binding.item = item
            binding.recyclerViewItemClickCallback = recyclerViewItemClickCallback
            binding.executePendingBindings()
        }
    }

}