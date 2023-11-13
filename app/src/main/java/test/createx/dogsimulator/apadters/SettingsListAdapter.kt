package test.createx.dogsimulator.apadters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.SettingsListItem
import test.createx.dogsimulator.databinding.SettingsListItemBinding


class SettingsListAdapter(
    private val onPrivacyPolicyClick: () -> Unit, private val onShareClick: () -> Unit
) : ListAdapter<SettingsListItem, SettingsListAdapter.SettingsViewHolder>(SettingsListItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SettingsListItemBinding.inflate(inflater, parent, false)
        return SettingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    onPrivacyPolicyClick()
                }

                1 -> {
                    onPrivacyPolicyClick()
                }

                3 -> {
                    onShareClick()
                }
            }
        }
    }

    inner class SettingsViewHolder(private val binding: SettingsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsListItem) {
            binding.settingsListItemImage.setImageResource(item.icon)
            binding.settingsListItemText.text = item.title
        }
    }

    class SettingsListItemComparator : DiffUtil.ItemCallback<SettingsListItem>() {
        override fun areItemsTheSame(
            oldItem: SettingsListItem, newItem: SettingsListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SettingsListItem, newItem: SettingsListItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
