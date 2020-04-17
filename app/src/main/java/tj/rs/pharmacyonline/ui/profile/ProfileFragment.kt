package tj.rs.pharmacyonline.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.android.synthetic.main.fragment_profile.*
import tj.rs.pharmacyonline.R

/**
 * Created by Rustam Safarov (RS) on 16.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ProfileFragment : Fragment() {
    private var options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val myPurchasesView = my_purchases_root_view
        myPurchasesView.setOnClickListener {
            findNavController().navigate(R.id.myPurchasesFragment, null, options)
        }
    }

}