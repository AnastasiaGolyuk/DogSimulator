package test.createx.dogsimulator.apadters

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.SimulatorGridItem
import test.createx.dogsimulator.databinding.GridItemBinding
import java.io.IOException

class SimulatorGridAdapter : RecyclerView.Adapter<SimulatorGridAdapter.SimulatorViewHolder>() {

    private var items: List<SimulatorGridItem> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<SimulatorGridItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimulatorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridItemBinding.inflate(inflater, parent, false)
        return SimulatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimulatorViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            item.isSelected = true
            for (itm in items) {
                if (itm != item && itm.isSelected) {
                    itm.isSelected = false
                    break
                }
            }
            notifyDataSetChanged()
            val mediaPlayer = MediaPlayer()
            try {
                mediaPlayer.setDataSource(item.soundUrl)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
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
    }
}