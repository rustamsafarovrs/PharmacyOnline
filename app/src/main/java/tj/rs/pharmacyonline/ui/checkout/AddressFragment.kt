package tj.rs.pharmacyonline.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.address_fragment.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.AddressFragmentBinding
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder

class AddressFragment : Fragment() {

    companion object {
        fun newInstance() = AddressFragment()
    }

    private lateinit var viewModel: AddressFragmentViewModel
    private lateinit var binding: AddressFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.address_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddressFragmentViewModel::class.java)

        binding.viewModel = viewModel

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == -1) {
                chip1.isChecked = true
            }
        }

        calendarView.minDate = viewModel.minDate.time
        calendarView.date = viewModel.date.time
        calendarView.setOnDateChangeListener(onDateChangeListener)

        viewModel.onBuyStep2BtnClick.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.paymentFragment,
                    Bundle.EMPTY,
                    getSlideLeftAnimBuilder().build()
                )
            }
        })


    }

    val onDateChangeListener =
        CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.date.month = month
            viewModel.date.date = dayOfMonth

        }

}