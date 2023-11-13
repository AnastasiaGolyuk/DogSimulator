package test.createx.dogsimulator.apadters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.SubscriptionListItem
import test.createx.dogsimulator.databinding.SubscriptionListItemBinding

class SubscriptionListAdapter :
    ListAdapter<SubscriptionListItem, SubscriptionListAdapter.SubscriptionViewHolder>(
        SimulatorGridItemComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubscriptionListItemBinding.inflate(inflater, parent, false)
        return SubscriptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class SubscriptionViewHolder(private val binding: SubscriptionListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SubscriptionListItem) {
            binding.listItemImage.setImageResource(item.img)
            binding.listItemText.text = item.title
        }
    }

    class SimulatorGridItemComparator : DiffUtil.ItemCallback<SubscriptionListItem>() {
        override fun areItemsTheSame(
            oldItem: SubscriptionListItem, newItem: SubscriptionListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SubscriptionListItem, newItem: SubscriptionListItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}