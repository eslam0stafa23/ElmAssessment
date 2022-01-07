package com.assessment.elm.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.assessment.elm.R
import com.assessment.elm.data.requests.ChangeIncidentStatusBody
import com.assessment.elm.databinding.SheetChangeIncidentBinding
import com.assessment.elm.utils.appComponent
import com.assessment.elm.utils.result.ResourceType
import com.assessment.elm.utils.showErrorMessage
import com.assessment.elm.utils.showLoading
import com.assessment.elm.utils.viewmodel.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class ChangeIncidentStatusBottomSheet : BottomSheetDialogFragment() {
  @Inject lateinit var viewModelFactory: ViewModelFactory
  private val homeViewModel: HomeViewModel by activityViewModels { viewModelFactory }
  private val args: ChangeIncidentStatusBottomSheetArgs by navArgs()

  private lateinit var binding: SheetChangeIncidentBinding
  private var selectedStatusId: Int = 0

  companion object {
    @JvmStatic
    fun show(navController: NavController, incidentId: String) {
      val action =
        ChangeIncidentStatusBottomSheetDirections.actionNavigateToNavigationChangeStatusSheet(
          incidentId
        )
      navController.navigate(action)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val rootView: View = inflater.inflate(R.layout.sheet_change_incident, container, false)
    binding = SheetChangeIncidentBinding.bind(rootView)
    appComponent.inject(this)
    return rootView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    setupActions()
    setupIncidentStatusSpinner()
  }

  private fun setupActions() {
    binding.btnSubmit.setOnClickListener {
      if (selectedStatusId > 0) {
        changeIncidentStatus(ChangeIncidentStatusBody(args.incidentId, selectedStatusId))
      } else {
        setError(getString(R.string.select_status))
      }
    }
  }

  private fun changeIncidentStatus(changeIncidentStatusBody: ChangeIncidentStatusBody) {
    homeViewModel.changeIncidentStatus(changeIncidentStatusBody).observe(viewLifecycleOwner, {
      when (it.resourceType) {
        ResourceType.SUCCESS -> {
          setLoading(false)
          dismiss()
        }
        ResourceType.LOADING -> {
          setLoading(true)
        }
        ResourceType.ERROR -> {
          setLoading(false)
          setError(getString(R.string.change_status_error))
        }
      }
    })
  }

  private fun setupIncidentStatusSpinner() {
    val incidentsStatusList =
      resources.getStringArray(R.array.incident_status_list)
    val spinnerArrayAdapter: ArrayAdapter<String?> = object : ArrayAdapter<String?>(
      requireActivity(), R.layout.item_spinner, incidentsStatusList
    ) {
      override fun isEnabled(position: Int): Boolean {
        return position != 0
      }

      override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
      ): View {
        val view = super.getDropDownView(position, convertView, parent)
        val tvTitle = view as TextView
        tvTitle.setTextColor(
          if (position == 0) Color.GRAY else ContextCompat.getColor(
            parent.context,
            R.color.color_light_black_text
          )
        )
        return view
      }
    }

    spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner)
    binding.spinnerIncidentStatus.adapter = spinnerArrayAdapter
    binding.spinnerIncidentStatus.onItemSelectedListener =
      object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
          when {
            getString(R.string.status_1) ==
              binding.spinnerIncidentStatus.selectedItem.toString() -> {
              selectedStatusId = 0
            }
            getString(R.string.status_2) ==
              binding.spinnerIncidentStatus.selectedItem.toString() -> {
              selectedStatusId = 1
            }
            getString(R.string.status_3) ==
              binding.spinnerIncidentStatus.selectedItem.toString() -> {
              selectedStatusId = 2
            }
            getString(R.string.status_4) ==
              binding.spinnerIncidentStatus.selectedItem.toString() -> {
              selectedStatusId = 3
            }
            getString(R.string.status_5) ==
              binding.spinnerIncidentStatus.selectedItem.toString() -> {
              selectedStatusId = 4
            }
            getString(R.string.status_6) ==
              binding.spinnerIncidentStatus.selectedItem.toString() -> {
              selectedStatusId = 5
            }
          }
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
      }
  }

  private fun setLoading(isLoading: Boolean) = binding.layoutProgress.showLoading(isLoading)

  private fun setError(msg: String) = binding.layoutProgress.showErrorMessage(msg)
}