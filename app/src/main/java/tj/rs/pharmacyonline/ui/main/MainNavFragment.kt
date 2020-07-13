package tj.rs.pharmacyonline.ui.main


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.FragmentMainNavBinding
import tj.rs.pharmacyonline.ui.lastmedicine.LastMedicineRVAdapter
import tj.rs.pharmacyonline.ui.lastmedicine.LastMedicineViewModel
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder

/**
 * A simple [Fragment] subclass.
 */
class MainNavFragment : Fragment(), LastMedicineRVAdapter.OnItemClickListener {

    lateinit var binding: FragmentMainNavBinding
    private val lastMedicineRVAdapter =
        LastMedicineRVAdapter(
            arrayListOf(),
            this
        )
    lateinit var lastMedicineViewModel: LastMedicineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_nav, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onItemClick(position: Int) {
        val action =
            MainNavFragmentDirections.actionMainNavFragmentToMedicineDetailsFragment(
                lastMedicineRVAdapter.getItemByPosition(position).id
            )
        findNavController().navigate(action, getSlideLeftAnimBuilder().build())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lastMedicineViewModel = ViewModelProvider(this).get(LastMedicineViewModel::class.java)
        binding.lastMedicineViewModel = lastMedicineViewModel

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.includeLastMedicine.repositoryRv.layoutManager =
                GridLayoutManager(requireContext(), 2)
        } else {
            binding.includeLastMedicine.repositoryRv.layoutManager =
                GridLayoutManager(requireContext(), 5)
        }

        binding.includeLastMedicine.repositoryRv.setItemViewCacheSize(20)

        binding.includeLastMedicine.repositoryRv.adapter = lastMedicineRVAdapter

        lastMedicineViewModel.repository.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    lastMedicineRVAdapter.replaceData(it)
                }
            })

        val button = view.findViewById<MaterialButton>(R.id.mb_refresh)
        button.setOnClickListener {
            lastMedicineViewModel.loadLastMedicine()
        }
        lastMedicineViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                showInternetConnection()
            }
        })
    }

    private fun showInternetConnection() {
        if (!lastMedicineViewModel.netManager.isConnectedToInternet()) {
            Snackbar.make(binding.root, "No internet connection", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()

        if (!lastMedicineViewModel.authRepository.getAuthorized()) {
            activity?.finish()
        }
    }
}
