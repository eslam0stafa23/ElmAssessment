package com.assessment.elm.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import com.assessment.elm.R
import com.assessment.elm.data.models.IncidentData
import com.assessment.elm.databinding.FragmentDashboardBinding
import com.assessment.elm.utils.Utils
import com.assessment.elm.utils.appComponent
import com.assessment.elm.utils.result.ResourceType
import com.assessment.elm.utils.showErrorMessage
import com.assessment.elm.utils.showLoading
import com.assessment.elm.utils.viewmodel.ViewModelFactory
import com.assessment.elm.utils.views.BaseFragment
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import javax.inject.Inject

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

  @Inject lateinit var viewModelFactory: ViewModelFactory
  private val dashboardViewModel: DashboardViewModel by activityViewModels { viewModelFactory }

  companion object {
    @JvmStatic
    fun show(navController: NavController) {
      navController.navigate(R.id.action_navigate_to_navigation_dashboard)
    }
  }

  override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentDashboardBinding {
    appComponent.inject(this)
    return FragmentDashboardBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getIncidentsData()
  }

  private fun getIncidentsData() {
    dashboardViewModel.getDashboardData().observe(viewLifecycleOwner, {
      when (it.resourceType) {
        ResourceType.SUCCESS -> {
          showPieChart(it.data!!)
          setLoading(false)
        }
        ResourceType.LOADING -> {
          setLoading(true)
        }
        ResourceType.ERROR -> {
          setLoading(false)
          setError(getString(R.string.error_fetching_incidents_data))
        }
      }
    })
  }

  private fun showPieChart(data: List<IncidentData>) {
    val pieEntries: ArrayList<PieEntry> = ArrayList()

    val dataMap: MutableMap<String, Int> = HashMap()
    val colors: ArrayList<Int> = ArrayList()

    data.forEach {
      dataMap[Utils.getIncidentStatusString(it.status)] = it.count.status
      colors.add(resources.getColor(Utils.priorityColor(it.status)))
    }

    //input data and fit data into pie chart entry
    for (type in dataMap.keys) {
      pieEntries.add(PieEntry(dataMap[type]!!.toFloat(), type))
    }

    val pieDataSet = PieDataSet(pieEntries, "")
    pieDataSet.valueTextSize = 22f
    pieDataSet.colors = Utils.getStatusColorsList(resources)

    val pieData = PieData(pieDataSet)
    pieData.setDrawValues(true)
    binding.pieChartView.centerText = getString(R.string.incidents_status)
    binding.pieChartView.setCenterTextSize(24f)
    binding.pieChartView.setCenterTextColor(resources.getColor(R.color.color_gray_text))
    binding.pieChartView.description.isEnabled = false
    binding.pieChartView.isRotationEnabled = true
    binding.pieChartView.setEntryLabelTextSize(18f)
    binding.pieChartView.legend.isEnabled = false
    binding.pieChartView.data = pieData
    binding.pieChartView.invalidate()
  }

  private fun setLoading(isLoading: Boolean) = binding.layoutProgress.showLoading(isLoading)

  private fun setError(msg: String) = binding.layoutProgress.showErrorMessage(msg)
}