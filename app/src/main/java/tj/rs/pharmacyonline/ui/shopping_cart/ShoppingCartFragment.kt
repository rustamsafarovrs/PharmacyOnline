package tj.rs.pharmacyonline.ui.shopping_cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.ShoppingCartFragmentBinding
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder


class ShoppingCartFragment : Fragment() {

    lateinit var viewModel: ShoppingCartViewModel
    lateinit var binding: ShoppingCartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.shopping_cart_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter =
            ShoppingCartRVAdapter(viewModel.list, viewModel.listener, viewModel, requireActivity())

        viewModel.notifyItemRemoved.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                (binding.recyclerView.adapter as ShoppingCartRVAdapter).notifyItemRemoved(it)
            }
        })

        viewModel.openAddressFragment.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.addressFragment,
                    Bundle.EMPTY,
                    getSlideLeftAnimBuilder().build()
                )
            }
        })
    }
}