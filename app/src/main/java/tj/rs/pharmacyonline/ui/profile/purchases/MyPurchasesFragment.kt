package tj.rs.pharmacyonline.ui.profile.purchases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_my_purchases.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.FragmentMyPurchasesBinding

class MyPurchasesFragment : Fragment(), MyPurchasesRVAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = MyPurchasesFragment()
    }

    private lateinit var binding: FragmentMyPurchasesBinding
    private lateinit var viewModel: MyPurchasesViewModel

    private val myPurchasesRVAdapter = MyPurchasesRVAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_my_purchases, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyPurchasesViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.isLoading.observe(this) {
            if (it == true) {
                showInternetConnection()
            }
        }
        val recyclerView = repository_rv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = myPurchasesRVAdapter

        viewModel.repository.observe(viewLifecycleOwner, Observer {
            myPurchasesRVAdapter.replaceData(it)
        })
    }

    private fun showInternetConnection() {
        if (!viewModel.netManager.isConnectedToInternet()) {
            Snackbar.make(binding.root, "No internet connection", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onItemClick(position: Int) {

    }

}
