package tj.rs.pharmacyonline.ui.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.banner_fragment.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.BannerFragmentBinding
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder

class BannerFragment : Fragment() {

    private lateinit var viewModel: BannerViewModel
    private lateinit var binding: BannerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.banner_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(BannerViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.bitmap.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                iv_banner.setImageBitmap(it)
            }
        })

        mb_close.setOnClickListener {
            openMainActivity()
        }

        mb_action.setOnClickListener {
            Snackbar.make(binding.root, "TODO: Implement this action", Snackbar.LENGTH_LONG).show()
        }

    }

    private fun openMainActivity() {
        findNavController().navigate(
            R.id.authorizedActivity,
            null,
            getSlideLeftAnimBuilder().build()
        )
        this.activity?.finish()
    }


}


