package test.createx.dogsimulator.apadters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.SimulatorGridItem
import test.createx.dogsimulator.databinding.GridItemBinding

class SimulatorGridAdapter(private val onClick: (SimulatorGridItem) -> Unit) :
    ListAdapter<SimulatorGridItem, SimulatorGridAdapter.SimulatorViewHolder>
        (SimulatorGridItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimulatorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridItemBinding.inflate(inflater, parent, false)
        return SimulatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimulatorViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun onBindViewHolder(
        holder: SimulatorViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.bindSelectedState(getItem(position).isSelected)
            }
        }
    }

    inner class SimulatorViewHolder(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimulatorGridItem) {
            binding.imageGridItem.setImageResource(item.img)
            binding.textGridItem.text = item.title
            if (item.isSelected) {
                binding.selectedGridItem.visibility = View.VISIBLE
            } else {
                binding.selectedGridItem.visibility = View.GONE
            }
        }

        fun bindSelectedState(isSelected: Boolean) {
            if (isSelected) {
                binding.selectedGridItem.visibility = View.VISIBLE
            } else {
                binding.selectedGridItem.visibility = View.GONE
            }
        }
    }

    class SimulatorGridItemComparator : DiffUtil.ItemCallback<SimulatorGridItem>() {
        override fun areItemsTheSame(
            oldItem: SimulatorGridItem, newItem: SimulatorGridItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: SimulatorGridItem, newItem: SimulatorGridItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: SimulatorGridItem, newItem: SimulatorGridItem): Any? {
            return if (oldItem.isSelected != newItem.isSelected) {
                true
            } else {
                null
            }
        }
    }
}