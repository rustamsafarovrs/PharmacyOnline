package tj.rs.pharmacyonline.ui.registration

import android.app.Activity
import android.app.ProgressDialog
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
import androidx.navigation.fragment.findNavController
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import com.redmadrobot.inputmask.MaskedTextChangedListener.ValueListener
import kotlinx.android.synthetic.main.registration_fragment.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.data.enums.RequestCodeEnums
import tj.rs.pharmacyonline.data.model.CatalogPhoneCountry
import tj.rs.pharmacyonline.databinding.RegistrationFragmentBinding
import tj.rs.pharmacyonline.ui.catalogs.phone.PhoneViewModel
import tj.rs.pharmacyonline.ui.catalogs.select.SelectCatalogDialog
import tj.rs.pharmacyonline.utils.getSlideLeftAnimBuilder


class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() =
            RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var phoneViewModel: PhoneViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)
        phoneViewModel = ViewModelProvider(this).get(PhoneViewModel::class.java)

        binding.viewModel = viewModel
        binding.phoneViewModel = phoneViewModel

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

        viewModel.showError.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                showNetworkError(it)
            }
        })

        viewModel.openConfirmFragment.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                openConfirmFragment()
            }
        })

        viewModel.phoneError.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                phoneError()
            }
        })


        phoneViewModel.showSelectCodeCatalog.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                val dialog = SelectCatalogDialog.newInstance(it)
                dialog.setTargetFragment(this, RequestCodeEnums.SELECT_CODE.id)
                dialog.show(fragmentManager!!, "")
            }
        })
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

    private fun showNetworkError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            RequestCodeEnums.SELECT_CODE.id -> {
                data?.extras?.let {
                    var code: CatalogPhoneCountry = it.getParcelable("selectedValue")!!
                    phoneViewModel.onCodeSelected(code)
                }
            }
        }
    }

}