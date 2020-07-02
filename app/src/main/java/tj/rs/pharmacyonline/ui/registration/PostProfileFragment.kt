package tj.rs.pharmacyonline.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.PostProfileFragmentBinding
import tj.rs.pharmacyonline.ui.AuthorizedActivity

class PostProfileFragment : Fragment() {

    companion object {
        fun newInstance() = PostProfileFragment()
    }

    private lateinit var viewModel: PostProfileViewModel
    private lateinit var binding: PostProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.post_profile_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostProfileViewModel::class.java)
        binding.viewModel = viewModel

        observe()
    }

    private fun observe() {
        viewModel.openMainActivity.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                openMainActivity()
            }
        })
        viewModel.onUpdateErrorHandler.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun openMainActivity() {
        startActivity(Intent(context, AuthorizedActivity::class.java))
        activity?.finish()
    }

}