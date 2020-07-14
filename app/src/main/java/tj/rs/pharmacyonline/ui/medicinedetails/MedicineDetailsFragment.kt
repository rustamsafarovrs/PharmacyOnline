package tj.rs.pharmacyonline.ui.medicinedetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.snackbar.Snackbar
import tj.rs.pharmacyonline.App
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.data.model.Price
import tj.rs.pharmacyonline.databinding.FragmentMedicineDetailsBinding
import tj.rs.pharmacyonline.ui.shopping_cart.ShoppingCartViewModel
import tj.rs.pharmacyonline.ui_commons.RecyclerViewItemClickCallback
import tj.rs.pharmacyonline.utils.view_model.ViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class MedicineDetailsFragment : Fragment() {

    private val args: MedicineDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentMedicineDetailsBinding
    private lateinit var viewModel: MedicineDetailsFragmentViewModel

    private lateinit var shoppingCartViewModel: ShoppingCartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_medicine_details, container, false)
        binding.lifecycleOwner = this

        val viewModelFactory = ViewModelFactory(App.getInstance())

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MedicineDetailsFragmentViewModel::class.java)
        viewModel.setArgs(args.id)
        binding.viewModel = viewModel

        shoppingCartViewModel =
            ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)

        binding.executePendingBindings()

        viewModel.medicine.observe(viewLifecycleOwner, Observer {
            binding.tvName.text = it.name
//            binding.tvPrice.text = it.price.toString()
            binding.tvDescription.text = it.desc
            (binding.recyclerView.adapter as PriceRVAdapter).submitList(it.prices)
        })

        return binding.root
    }

    val onRecyclerViewItemClickListener = object : RecyclerViewItemClickCallback {

        override fun onRecyclerViewItemClick(any: Any) {
            when (any) {
                is Price -> {
                    showSnackbar()

                    val medicine = viewModel.medicine.value
                    medicine?.let {
                        shoppingCartViewModel.add(it, any)
                    }
                }
            }
        }
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, "Добавлено в корзину", Snackbar.LENGTH_SHORT)
            .setAction("Отмена") {
                shoppingCartViewModel.deleteLastItem()
            }
            .show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = PriceRVAdapter(onRecyclerViewItemClickListener)
//        binding.recyclerView.addOnScrollListener(viewModel.onScrollListener)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)

        observer()
    }

    private fun observer() {


    }


}
