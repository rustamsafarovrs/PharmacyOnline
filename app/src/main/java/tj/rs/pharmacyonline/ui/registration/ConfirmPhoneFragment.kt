package tj.rs.pharmacyonline.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import kotlinx.android.synthetic.main.fragment_confirm_phone.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.FragmentConfirmPhoneBinding
import tj.rs.pharmacyonline.ui_commons.base.BaseFragment


class ConfirmPhoneFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            ConfirmPhoneFragment()
    }

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: FragmentConfirmPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_phone, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)
        binding.viewModel = viewModel

        val toolbar = (activity as AppCompatActivity?)!!.supportActionBar
        toolbar?.title = viewModel.getFormattedPhoneNumber()

        observe()

        val listener = installOn(
            et_request_sms_code,
            "[0000]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    viewModel.requestSmsFieldText.value = extractedValue
                    bt_done.isEnabled = maskFilled
                }
            })
        et_request_sms_code.hint = listener.placeholder()

        bt_done.setOnClickListener {
            showProgressDialog()
            tv_error.text = ""
            viewModel.confirmPhone()
        }

        textView.setOnClickListener {
            MaterialAlertDialogBuilder(this.requireContext())
                .setTitle("Sample SMS Code")
                .setMessage("sms code dar vaqti sanai darkhost (GMT +00:00) generatsiya meshavad. ya`ne sanai darkhosti sms 17:00 boshad 1200 sms code meshavad.")
                .show()
        }

        bt_resent_request.setOnClickListener {
            viewModel.requestSmsCode()
            showProgressDialog()
        }
    }

    private fun observe() {
        viewModel.showError.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                showNetworkError(it)
            }
        })

        viewModel.openPostProfileFragment.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.postProfileFragment)
                dismissProgressDialog()

            }
        })

        viewModel.smsCodeError.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                smsCodeError()
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                dismissProgressDialog()
            }
        })
    }

    private fun smsCodeError() {
        tv_error.text = "SMS code error"
    }


    private fun showNetworkError(message: String) {
        tv_error.text = message
    }

}