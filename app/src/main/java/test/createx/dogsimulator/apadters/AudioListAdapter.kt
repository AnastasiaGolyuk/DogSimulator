package test.createx.dogsimulator.apadters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.data.models.GridItem
import test.createx.dogsimulator.playback.AudioPlayer

class AudioListAdapter(private val context: Context, private val audioRecords: List<AudioRecord>) : RecyclerView.Adapter<AudioListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val audioName: TextView = itemView.findViewById(R.id.audioFileName)
    }

    private val player:AudioPlayer = AudioPlayer(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.audio_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val audioRecord = audioRecords[position]
         holder.audioName.text = audioRecord.file.nameWithoutExtension
        var clicked = false

        holder.itemView.setOnClickListener {
            if (!clicked){
                player.playFile(audioRecord.file)
                clicked=true
            } else {
                player.pause()
                clicked=false
            }

        }
    }

    override fun getItemCount(): Int {
        return audioRecords.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}