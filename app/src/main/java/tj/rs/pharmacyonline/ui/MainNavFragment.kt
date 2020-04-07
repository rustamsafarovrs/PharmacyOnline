package tj.rs.pharmacyonline.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.adapter.LastMedicineRVAdapter
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.databinding.FragmentMainNavBinding
import tj.rs.pharmacyonline.ui.lastmedicine.LastMedicineViewModel

/**
 * A simple [Fragment] subclass.
 */
class MainNavFragment : Fragment(), LastMedicineRVAdapter.OnItemClickListener {

    lateinit var binding: FragmentMainNavBinding
    private val lastMedicineRVAdapter = LastMedicineRVAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_nav, container, false)
        val navController = NavHostFragment.findNavController(this)
        val lastMedicineViewModel = ViewModelProvider(this).get(LastMedicineViewModel::class.java)
        binding.lastMedicineViewModel = lastMedicineViewModel
        binding.executePendingBindings()

        binding.includeLastMedicine.repositoryRv.layoutManager =
            GridLayoutManager(requireContext(), 3)
        binding.includeLastMedicine.repositoryRv.adapter = lastMedicineRVAdapter

        lastMedicineViewModel.loadLastMedicine()

        lastMedicineViewModel.repository.observe(
            this,
            Observer<ArrayList<Medicine>> { it?.let { lastMedicineRVAdapter.replaceData(it) } })

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        return binding.root
    }

    override fun onItemClick(position: Int) {
        Snackbar.make(
            binding.root,
            lastMedicineRVAdapter.getItemByPosition(position).name + " :: clicked",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
