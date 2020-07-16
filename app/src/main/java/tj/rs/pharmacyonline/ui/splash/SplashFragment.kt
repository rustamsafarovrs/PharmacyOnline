package tj.rs.pharmacyonline.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.splash_fragment.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.ui.banner.BannerViewModel
import tj.rs.pharmacyonline.utils.getFadeOutAnimBuilder


class SplashFragment : Fragment() {

    companion object {
        private var handled = false
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
        bannerViewModel = ViewModelProvider(requireActivity()).get(BannerViewModel::class.java)

        Handler().postDelayed({
            startAnimation()
        }, 2000)
    }

    private fun startAnimation() {
        motion_layout.transitionToEnd()
        motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                Handler().postDelayed({
                    navigateNext()
                    handled = true
                }, 200)
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                // do nothing
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                // do nothing
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float
            ) {
                // do nothing
            }
        })
    }

    private fun navigateNext() {
        if (!handled) {
            if (viewModel.isAuthorized()) {
                openBannerOrMainActivity()
            } else {
                findNavController().navigate(
                    R.id.unauthorizedActivity,
                    null,
                    getFadeOutAnimBuilder().build()
                )
                this@SplashFragment.requireActivity().finish()
            }
        }
    }

    private fun openBannerOrMainActivity() {
        if (bannerViewModel.banner.value!!.status) {
            findNavController().navigate(
                R.id.bannerFragment,
                null,
                getFadeOutAnimBuilder().build()
            )
        } else {
            findNavController().navigate(
                R.id.authorizedActivity,
                null,
                getFadeOutAnimBuilder().build()
            )
            this.requireActivity().finish()
        }
    }

    override fun onStop() {
        super.onStop()

        handled = false
    }
}