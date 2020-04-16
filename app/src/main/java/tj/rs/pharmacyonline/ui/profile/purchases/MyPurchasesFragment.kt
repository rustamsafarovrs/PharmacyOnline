package tj.rs.pharmacyonline.ui.profile.purchases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tj.rs.pharmacyonline.R

class MyPurchasesFragment : Fragment() {

    companion object {
        fun newInstance() = MyPurchasesFragment()
    }

    private lateinit var viewModel: MyPurchasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_purchases, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyPurchasesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
