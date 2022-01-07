package com.assessment.elm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assessment.elm.R
import com.assessment.elm.databinding.FragmentLoginBinding
import com.assessment.elm.ui.dashboard.DashboardFragment
import com.assessment.elm.utils.SessionManager
import com.assessment.elm.utils.appComponent
import com.assessment.elm.utils.views.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

  @Inject lateinit var sessionManager: SessionManager

  override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
    appComponent.inject(this)
    return FragmentLoginBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    checkLoginStatus()
    setupActions()
  }

  private fun checkLoginStatus() {
    if (sessionManager.accessToken?.isNotEmpty() == true) DashboardFragment.show(navController)
  }

  private fun setupActions() {
    binding.btnNext.setOnClickListener {
      if (binding.etEmail.text.isBlank() || binding.etEmail.text.isEmpty()) {
        binding.etEmail.error = getString(R.string.email_error)
      } else {
        VerifyOtpFragment.show(navController, binding.etEmail.text.toString())
      }
    }
  }

}