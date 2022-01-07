package com.assessment.elm.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.elm.R
import com.assessment.elm.data.models.Incident
import com.assessment.elm.databinding.FragmentHomeBinding
import com.assessment.elm.ui.adapters.IncidentsAdapter
import com.assessment.elm.utils.SpinnerUtils
import com.assessment.elm.utils.Utils.convertToDateOnly
import com.assessment.elm.utils.Utils.getIncidentStatusInt
import com.assessment.elm.utils.appComponent
import com.assessment.elm.utils.result.ResourceType.ERROR
import com.assessment.elm.utils.result.ResourceType.LOADING
import com.assessment.elm.utils.result.ResourceType.SUCCESS
import com.assessment.elm.utils.showErrorMessage
import com.assessment.elm.utils.showLoading
import com.assessment.elm.utils.viewmodel.ViewModelFactory
import com.assessment.elm.utils.views.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(), IncidentsAdapter.IncidentActionsListener {

  @Inject lateinit var viewModelFactory: ViewModelFactory
  private val homeViewModel: HomeViewModel by activityViewModels { viewModelFactory }
  private val incidentsAdapter: IncidentsAdapter by lazy { IncidentsAdapter(this) }
  private lateinit var incidentsList: MutableList<Incident>
  private lateinit var datesArrayAdapter: ArrayAdapter<String>
  private lateinit var statusArrayAdapter: ArrayAdapter<String>

  override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
    appComponent.inject(this)
    return FragmentHomeBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupViews()
    setupDateFilterSpinner()
    setupStatusFilterSpinner()
    getIncidentsList()
    setupActions()
  }

  private fun setupViews() {
    binding.rvIncidents.layoutManager = LinearLayoutManager(context)
    binding.rvIncidents.adapter = incidentsAdapter
  }

  private fun getIncidentsList() {
    homeViewModel.getIncidentsList().observe(viewLifecycleOwner, {
      when (it.resourceType) {
        SUCCESS -> {
          incidentsList = it.data?.toMutableList()!!
          submitDatesForFiltering()
          submitStatusForFiltering()
          incidentsAdapter.submitList(incidentsList)
          setLoading(false)
        }
        LOADING -> {
          setLoading(true)
        }
        ERROR -> {
          setLoading(false)
          setError(getString(R.string.incidents_list_error))
        }
      }
    })
  }

  @SuppressLint("NotifyDataSetChanged")
  private fun setupActions() {
    binding.btnReset.setOnClickListener {
      incidentsAdapter.submitList(incidentsList)
      incidentsAdapter.notifyDataSetChanged()
      binding.spinnerDateFilter.setSelection(0)
      binding.spinnerStatusFilter.setSelection(0)
    }
  }

  private fun submitDatesForFiltering() {
    incidentsList.forEach {
      it.createdAtDate = convertToDateOnly(it.createdAt)
    }
    val datesList = mutableListOf<String>()
    datesList.add(getString(R.string.select_date))
    incidentsList.forEach {
      if (!datesList.contains(it.createdAtDate)) datesList.add(it.createdAtDate!!)
    }
    datesArrayAdapter.addAll(datesList)
  }

  private fun setupDateFilterSpinner() {
    datesArrayAdapter = SpinnerUtils.createArrayAdapter(requireContext())
    binding.spinnerDateFilter.adapter = datesArrayAdapter
    binding.spinnerDateFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(
        adapterView: AdapterView<*>?,
        view: View,
        position: Int,
        l: Long
      ) {
        if (binding.spinnerDateFilter.selectedItem.toString() != getString(R.string.select_date)) {
          filterListByDate(binding.spinnerDateFilter.selectedItem.toString())
          binding.spinnerStatusFilter.setSelection(0)
        }
      }

      override fun onNothingSelected(adapterView: AdapterView<*>?) {}
    }
  }

  private fun submitStatusForFiltering() {
    val statusList = resources.getStringArray(R.array.incident_status_list)
    statusList.forEach {
      statusArrayAdapter.add(it)
    }
  }

  private fun setupStatusFilterSpinner() {
    statusArrayAdapter = SpinnerUtils.createArrayAdapter(requireContext())
    binding.spinnerStatusFilter.adapter = statusArrayAdapter
    binding.spinnerStatusFilter.onItemSelectedListener =
      object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
          adapterView: AdapterView<*>?,
          view: View,
          position: Int,
          l: Long
        ) {
          if (binding.spinnerStatusFilter.selectedItem.toString() != getString(R.string.status_1)) {
            filterListByStatus(binding.spinnerStatusFilter.selectedItem.toString())
            binding.spinnerDateFilter.setSelection(0)
          }
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
      }
  }

  @SuppressLint("NotifyDataSetChanged")
  private fun filterListByDate(filterString: String) {
    val filteredList = incidentsList.filter { it.createdAtDate == filterString }
    incidentsAdapter.submitList(filteredList)
    incidentsAdapter.notifyDataSetChanged()
  }

  @SuppressLint("NotifyDataSetChanged")
  private fun filterListByStatus(filterString: String) {
    val filteredList = incidentsList.filter { it.status == getIncidentStatusInt(filterString) }
    incidentsAdapter.submitList(filteredList)
    incidentsAdapter.notifyDataSetChanged()
  }

  private fun setLoading(isLoading: Boolean) = binding.layoutProgress.showLoading(isLoading)

  private fun setError(msg: String) = binding.layoutProgress.showErrorMessage(msg)

  override fun onIncidentClick(incident: Incident) {
    ChangeIncidentStatusBottomSheet.show(navController, incident.id)
  }

}