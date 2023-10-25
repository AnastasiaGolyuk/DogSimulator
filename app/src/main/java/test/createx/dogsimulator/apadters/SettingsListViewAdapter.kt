package test.createx.dogsimulator.apadters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.SettingsListItem
import test.createx.dogsimulator.ui.views.activities.WebViewActivity


class SettingsListViewAdapter (private val context: Context?, private val list: List<SettingsListItem>) :

    BaseAdapter() {

    override fun isEnabled(position: Int): Boolean {
        return false
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): SettingsListItem {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        view = LayoutInflater.from(context).inflate(R.layout.settings_list_item, parent, false)
        val title = view.findViewById<TextView>(R.id.settingsListItemText)
        val img = view.findViewById<ImageView>(R.id.settingsListItemImage)
        val currentItem = getItem(position)
        img.setImageResource(currentItem.icon)
        title.text = currentItem.title

        view.setOnClickListener {
            when (position) {
                0 -> {
                    val intent = Intent(context, WebViewActivity::class.java)
                    intent.putExtra("url","https://www.google.com/")
                    context?.startActivity(intent)
                }
                1 -> {
                    val intent = Intent(context, WebViewActivity::class.java)
                    intent.putExtra("url","https://www.google.com/")
                    context?.startActivity(intent)
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

        return view
    }

}