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
import kotlinx.android.synthetic.main.fragment_medicine_details.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.FragmentMedicineDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class MedicineDetailsFragment : Fragment() {

    private val args: MedicineDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentMedicineDetailsBinding
    private lateinit var viewModel: MedicineDetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_medicine_details, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MedicineDetailsFragmentViewModel::class.java)
        viewModel.setArgs(args.id)
        binding.viewModel = viewModel

        binding.executePendingBindings()

        viewModel.medicine.observe(this, Observer {
            binding.tvName.text = it.name
            binding.tvPrice.text = it.price.toString()
            binding.tvDescription.text = it.desc
        })

        return binding.root
    }
}
