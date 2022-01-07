package com.assessment.elm.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.assessment.elm.R
import java.util.ArrayList

object SpinnerUtils {

  fun <TYPE> createArrayAdapter(
    context: Context
  ): ArrayAdapter<TYPE> {
    return object : ArrayAdapter<TYPE>(context, R.layout.item_spinner, ArrayList<TYPE>()) {
      override fun isEnabled(position: Int): Boolean {
        return position != 0
      }

      override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
      ): View {
        val view: View = super.getDropDownView(position, convertView, parent)
        val tv: TextView = view as TextView
        tv.setTextColor(
          if (position == 0) ContextCompat.getColor(
            context,
            R.color.color_gray_text
          ) else ContextCompat.getColor(context, R.color.color_black_text)
        )
        return view
      }
    }
  }
}