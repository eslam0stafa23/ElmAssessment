package com.assessment.elm.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.assessment.elm.R

class OtpTextWatcher internal constructor(
  private val currentView: View,
  private val nextView: View?,
  private val activity: FragmentActivity?
) :
  TextWatcher {
  override fun afterTextChanged(editable: Editable) {
    val text = editable.toString()
    when (currentView.id) {
      R.id.etCode1 -> if (text.length == 1) nextView!!.requestFocus()
      R.id.etCode2 -> if (text.length == 1) nextView!!.requestFocus()
      R.id.etCode3 -> if (text.length == 1) nextView!!.requestFocus()
      R.id.etCode4 -> if (text.length == 1) activity!!.hideSoftKeyboard()
    }
  }

  override fun beforeTextChanged(
    arg0: CharSequence,
    arg1: Int,
    arg2: Int,
    arg3: Int
  ) {
  }

  override fun onTextChanged(
    arg0: CharSequence,
    arg1: Int,
    arg2: Int,
    arg3: Int
  ) {
  }

}

class OtpKeyEvent internal constructor(
  private val currentView: EditText,
  private val previousView: EditText?
) : View.OnKeyListener {
  override fun onKey(
    p0: View?,
    keyCode: Int,
    event: KeyEvent?
  ): Boolean {
    if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.etCode1 && currentView.text.isEmpty()) {
      //If current is empty then previous EditText's number will also be deleted
      previousView!!.text = null
      previousView.requestFocus()
      return true
    }
    return false
  }

}