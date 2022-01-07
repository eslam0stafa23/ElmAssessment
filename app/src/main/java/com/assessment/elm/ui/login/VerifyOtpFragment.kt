package com.assessment.elm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.assessment.elm.R
import com.assessment.elm.data.requests.LoginBody
import com.assessment.elm.databinding.FragmentVerifyOtpBinding
import com.assessment.elm.ui.dashboard.DashboardFragment
import com.assessment.elm.utils.OtpKeyEvent
import com.assessment.elm.utils.OtpTextWatcher
import com.assessment.elm.utils.SessionManager
import com.assessment.elm.utils.appComponent
import com.assessment.elm.utils.result.ResourceType.ERROR
import com.assessment.elm.utils.result.ResourceType.LOADING
import com.assessment.elm.utils.result.ResourceType.SUCCESS
import com.assessment.elm.utils.showErrorMessage
import com.assessment.elm.utils.showLoading
import com.assessment.elm.utils.viewmodel.ViewModelFactory
import com.assessment.elm.utils.views.BaseFragment
import javax.inject.Inject

class VerifyOtpFragment : BaseFragment<FragmentVerifyOtpBinding>() {

  @Inject lateinit var viewModelFactory: ViewModelFactory
  private val loginViewModel: LoginViewModel by activityViewModels { viewModelFactory }
  private val args: VerifyOtpFragmentArgs by navArgs()
  @Inject lateinit var sessionManager: SessionManager

  companion object {
    @JvmStatic
    fun show(navController: NavController, email: String) {
      val action = VerifyOtpFragmentDirections.actionNavigateToNavigationVerifyOtp(email)
      navController.navigate(action)
    }
  }

  override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentVerifyOtpBinding {
    appComponent.inject(this)
    return FragmentVerifyOtpBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupOtpEditTextFields()
    setupActions()
  }

  private fun setupOtpEditTextFields() {
    //OtpTextWatcher here works only for moving to next EditText when a number is entered
    // first parameter is the current EditText and second parameter is next EditText
    binding.etCode1.addTextChangedListener(
      OtpTextWatcher(
        binding.etCode1,
        binding.etCode2,
        activity
      )
    )
    binding.etCode2.addTextChangedListener(
      OtpTextWatcher(
        binding.etCode2,
        binding.etCode3,
        activity
      )
    )
    binding.etCode3.addTextChangedListener(
      OtpTextWatcher(
        binding.etCode3,
        binding.etCode4,
        activity
      )
    )
    binding.etCode4.addTextChangedListener(OtpTextWatcher(binding.etCode4, null, activity))

    //OtpKeyEvent here works for deleting the element and to switch back to previous EditText
    //first parameter is the current EditText and second parameter is previous EditText
    binding.etCode1.setOnKeyListener(OtpKeyEvent(binding.etCode1, null))
    binding.etCode2.setOnKeyListener(OtpKeyEvent(binding.etCode2, binding.etCode1))
    binding.etCode3.setOnKeyListener(OtpKeyEvent(binding.etCode3, binding.etCode2))
    binding.etCode4.setOnKeyListener(OtpKeyEvent(binding.etCode4, binding.etCode3))
  }

  private fun setupActions() {
    binding.btnLogin.setOnClickListener {
      if (getVerifyCode().length == 4) {
        login(LoginBody(args.email, getVerifyCode()))
      } else {
        setError(getString(R.string.enter_code_hint))
      }
    }
  }

  private fun login(loginBody: LoginBody) {
    loginViewModel.login(loginBody).observe(viewLifecycleOwner, {
      when (it.resourceType) {
        SUCCESS -> {
          sessionManager.accessToken = it.data
          setLoading(false)
          DashboardFragment.show(navController)
        }
        LOADING -> {
          setLoading(true)
        }
        ERROR -> {
          setLoading(false)
          setError(getString(R.string.login_error))
        }
      }
    })
  }

  private fun getVerifyCode(): String {
    return "${binding.etCode1.text}${binding.etCode2.text}${binding.etCode3.text}${binding.etCode4.text}"
  }

  private fun setLoading(isLoading: Boolean) = binding.layoutProgress.showLoading(isLoading)

  private fun setError(msg: String) = binding.layoutProgress.showErrorMessage(msg)

}