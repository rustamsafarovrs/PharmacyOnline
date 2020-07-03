package tj.rs.pharmacyonline.ui.profile.profilesettings

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.android.synthetic.main.fragment_profile_settings.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.FragmentProfileSettingsBinding
import tj.rs.pharmacyonline.ui.profile.SharedViewModel

class ProfileSettingsFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileSettingsFragment()
    }

    private var options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    private lateinit var viewModel: ProfileSettingsViewModel
    private lateinit var binding: FragmentProfileSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile_settings, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileSettingsViewModel::class.java)
        binding.viewModel = viewModel

        mb_edit_profile.setOnClickListener {
            findNavController().navigate(
                R.id.editProfileFragment,
                null,
                options
            )
        }

        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.updateEvent.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                if (it) {
                    viewModel.loadProfile()
                }
            }
        })

        viewModel.openSplashActivity.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Handler().postDelayed({
                    findNavController().navigate(R.id.splashActivity)
                    requireActivity().finish()
                }, 1000)
            }
        })
    }
}
