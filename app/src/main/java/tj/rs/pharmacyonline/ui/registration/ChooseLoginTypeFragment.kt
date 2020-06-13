package tj.rs.pharmacyonline.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.choose_login_type_fragment.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder

class ChooseLoginTypeFragment : Fragment() {

    companion object {
        fun newInstance() =
            ChooseLoginTypeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_login_type_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mb_login_with_phone_number.setOnClickListener {
            findNavController().navigate(
                R.id.registrationFragment, null,
                getSlideLeftAnimBuilder().build()
            )
        }
    }

}