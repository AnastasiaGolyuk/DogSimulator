package test.createx.dogsimulator.apadters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import test.createx.dogsimulator.data.models.GridItem
import test.createx.dogsimulator.R

class GridViewAdapter(private val context: Context?, private val list: List<GridItem>) :

    BaseAdapter() {

    override fun isEnabled(position: Int): Boolean {
        return false
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): GridItem {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        val title = view.findViewById<TextView>(R.id.textGridItem)
        val img = view.findViewById<ImageView>(R.id.imageGridItem)
        val topImg = view.findViewById<ImageView>(R.id.selectedGridItem)
        val currentItem = getItem(position)
        img.setImageResource(currentItem.img)
        title.text = currentItem.title

        if (currentItem.isSelected) {
            topImg.visibility = View.VISIBLE
        } else {
            topImg.visibility = View.GONE
        }

        view.setOnClickListener {
            currentItem.isSelected = true

            for (item in list) {
                if (item != currentItem && item.isSelected) {
                    item.isSelected = false
                    break
                }
            }

            notifyDataSetChanged()
        }
        return view
    }
}