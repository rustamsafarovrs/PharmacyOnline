package tj.rs.pharmacyonline.ui.registration

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import com.redmadrobot.inputmask.MaskedTextChangedListener.ValueListener
import kotlinx.android.synthetic.main.registration_fragment.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.utils.event.EventObserver
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder


class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() =
            RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(RegistrationViewModel::class.java)

        val listener =
            installOn(
                et_phone,
                " ([00]) [000]-[00]-[00]",
                object : ValueListener {
                    override fun onTextChanged(
                        maskFilled: Boolean,
                        extractedValue: String,
                        formattedValue: String
                    ) {
                        viewModel.phoneFieldText.value = extractedValue
                        viewModel.formattedPhone = formattedValue
                        bt_confirm_phone_number.isEnabled = maskFilled
                    }
                }
            )

        et_phone.hint = listener.placeholder()

        bt_confirm_phone_number.setOnClickListener {
            showProgressDialog()
            viewModel.requestSmsCode()
        }

        observe()
    }

    private fun observe() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                dismissProgressDialog()
            }
        })

        val registrationFragmentNavigation = EventObserver { event ->
            when (event) {
                is RegistrationFragmentNavigation.ShowNetworkError -> showNetworkError()
                is RegistrationFragmentNavigation.OpenConfirmFragment -> openConfirmFragment()
                is RegistrationFragmentNavigation.PhoneError -> phoneError()
            }
        }

        viewModel.emitter.observe(viewLifecycleOwner, registrationFragmentNavigation)
    }

    private fun phoneError() {
        tv_error.text = viewModel.errorPhoneField.value
    }

    private fun openConfirmFragment() {
        findNavController().navigate(
            R.id.confirmPhoneFragment,
            Bundle.EMPTY,
            getSlideLeftAnimBuilder().build()
        )
    }

    private fun showNetworkError() {
        Toast.makeText(context, "Network error", Toast.LENGTH_LONG)
            .show()
    }

    private var progressDialog: ProgressDialog? = null

    private fun showProgressDialog() {
        if (progressDialog == null || !progressDialog!!.isShowing) {
            progressDialog = ProgressDialog.show(context, "", "Загрузка", true, false)
        }
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

}