package tj.rs.pharmacyonline.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.ui.banner.BannerViewModel
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder
import tj.rs.pharmacyonline.utils.getSlideUpAnimBuilder


class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel
    private lateinit var bannerViewModel: BannerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        bannerViewModel = ViewModelProvider(activity!!).get(BannerViewModel::class.java)

        Handler().postDelayed({
            if (viewModel.isAuthorized()) {
                openBannerOrMainActivity()
            } else {
                findNavController().navigate(
                    R.id.unauthorizedActivity,
                    null,
                    getSlideLeftAnimBuilder().build()
                )
                this@SplashFragment.activity?.finish()
            }

        }, 2000)
    }

    private fun openBannerOrMainActivity() {
        if (bannerViewModel.banner.value!!.status) {
            findNavController().navigate(
                R.id.bannerFragment,
                null,
                getSlideUpAnimBuilder().build()
            )
        } else {
            findNavController().navigate(
                R.id.authorizedActivity,
                null,
                getSlideLeftAnimBuilder().build()
            )
            this@SplashFragment.activity!!.finish()
        }
    }
}