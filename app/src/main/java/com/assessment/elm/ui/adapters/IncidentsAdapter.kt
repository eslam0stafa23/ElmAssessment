package com.assessment.elm.ui.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assessment.elm.data.models.Incident
import com.assessment.elm.databinding.ItemIncidentBinding
import com.assessment.elm.utils.Utils.formatApiDateTime
import com.assessment.elm.utils.Utils.getIncidentStatusString
import com.assessment.elm.utils.Utils.priorityColor
import com.assessment.elm.utils.layoutInflater

class IncidentsAdapter(private val incidentActionsListener: IncidentActionsListener) :
  ListAdapter<Incident, IncidentsAdapter.IncidentViewHolder>(callback) {

  companion object {
    private val callback = object : DiffUtil.ItemCallback<Incident>() {
      override fun areItemsTheSame(oldItem: Incident, newItem: Incident) =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: Incident, newItem: Incident) =
        oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
    val binding = ItemIncidentBinding.inflate(parent.layoutInflater, parent, false)
    return IncidentViewHolder(binding)
  }

  override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class IncidentViewHolder(private val binding: ItemIncidentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val context = binding.root

    fun bind(incident: Incident) {
      binding.tvDescription.text = incident.description
      binding.tvCreatedAt.text = formatApiDateTime(incident.createdAt)
      binding.tvLastUpdate.text = formatApiDateTime(incident.updatedAt)
      binding.tvStatus.text = getIncidentStatusString(incident.status)
      binding.viewPriority.setBackgroundColor(context.resources.getColor(priorityColor(incident.priority)))
      binding.ivPhotos.visibility = if (incident.medias.isEmpty()) View.GONE else View.VISIBLE
      binding.ivLocation.visibility =
        if (incident.longitude > 0 && incident.latitude > 0) View.VISIBLE else View.GONE
      binding.layoutIncident.setOnClickListener { incidentActionsListener.onIncidentClick(incident) }
    }
  }

  interface IncidentActionsListener {
    fun onIncidentClick(incident: Incident)
  }

}