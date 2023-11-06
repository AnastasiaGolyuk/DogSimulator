package test.createx.dogsimulator.apadters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.SubscriptionListItem
import test.createx.dogsimulator.databinding.SubscriptionListItemBinding

class SubscriptionListAdapter : RecyclerView.Adapter<SubscriptionListAdapter.SubscriptionViewHolder>() {

    private var items: List<SubscriptionListItem> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<SubscriptionListItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubscriptionListItemBinding.inflate(inflater, parent, false)
        return SubscriptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class SubscriptionViewHolder(private val binding: SubscriptionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SubscriptionListItem) {
            binding.listItemImage.setImageResource(item.img)
            binding.listItemText.text = item.title
        }
    }
}