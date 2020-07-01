package tj.rs.pharmacyonline.ui.catalogs.select

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.DialogSelectCatalogBinding
import tj.rs.pharmacyonline.ui_commons.base.BaseBottomSheetDialogFragment
import tj.rs.pharmacyonline.utils.getDividerItemDecoration

/**
 * Created by Rustam Safarov (RS) on 30.06.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class SelectCatalogDialog() : BaseBottomSheetDialogFragment() {

    private lateinit var list: List<Any>

    private lateinit var binding: DialogSelectCatalogBinding
    private lateinit var viewModel: SelectCatalogViewModel

    companion object {
        fun newInstance(list: List<Any>): SelectCatalogDialog {
            val dialog = SelectCatalogDialog()
            dialog.list = list
            return dialog
        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.dialog_select_catalog, null, false
            )
        binding.lifecycleOwner = this

        dialog.setContentView(binding.root)

        setBottomSheetCallback(binding.root)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.addItemDecoration(
            getDividerItemDecoration(context = context!!, left = R.dimen.dp_16)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SelectCatalogViewModel::class.java)
        viewModel.setList(list)

        binding.viewModel = viewModel
        binding.recyclerView.adapter =
            SelectCatalogAdapter(viewModel.onRecyclerViewItemClickListener)

        observe()
    }

    private fun observe() {
        viewModel.list.observe(this, Observer {
            (binding.recyclerView.adapter as SelectCatalogAdapter).submitList(it)
        })

        viewModel.setResult.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                val bundle = Bundle()
                bundle.putParcelable("selectedValue", it)

                val intent = Intent()
                intent.putExtras(bundle)

                if (targetFragment != null) {
                    targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
                }
            }
        })

        viewModel.dismiss.observe(
            this,
            Observer {
                it.getContentIfNotHandled()?.let {
                    dismiss()
                }
            }
        )
    }
}