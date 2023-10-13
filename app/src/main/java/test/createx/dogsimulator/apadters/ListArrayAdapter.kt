package test.createx.dogsimulator.apadters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.ListItem

class ListArrayAdapter(private val context: Context?, private val list: List<ListItem>) :

    BaseAdapter() {

    override fun isEnabled(position: Int): Boolean {
        return false
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): ListItem {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val title = view.findViewById<TextView>(R.id.listItemText)
        val img = view.findViewById<ImageView>(R.id.listItemImage)
        val currentItem = getItem(position)

        img.setImageResource(currentItem.img)
        title.text = currentItem.title
        return view
    }
}