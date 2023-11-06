package test.createx.dogsimulator.apadters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.SettingsListItem
import test.createx.dogsimulator.databinding.SettingsListItemBinding
import test.createx.dogsimulator.ui.webview.WebViewActivity


class SettingsListAdapter : RecyclerView.Adapter<SettingsListAdapter.SettingsViewHolder>() {

    private var items: List<SettingsListItem> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<SettingsListItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SettingsListItemBinding.inflate(inflater, parent, false)
        return SettingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    val intent = Intent(context, WebViewActivity::class.java)
                    intent.putExtra("url","https://www.google.com/")
                    context.startActivity(intent)
                }
                1 -> {
                    val intent = Intent(context, WebViewActivity::class.java)
                    intent.putExtra("url","https://www.google.com/")
                    context.startActivity(intent)
                }
                2 -> {

                }
                3 -> {
                    val intent= Intent()
                    intent.action=Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app: Dog Simulator")
                    intent.type="text/plain"
                    context?.startActivity(Intent.createChooser(intent,"Share To:"))
                }
            }
        }
    }

    inner class SettingsViewHolder(private val binding: SettingsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsListItem) {
            binding.settingsListItemImage.setImageResource(item.icon)
            binding.settingsListItemText.text = item.title
        }
    }
}
