package test.createx.dogsimulator.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import test.createx.dogsimulator.R
import test.createx.dogsimulator.apadters.GridViewAdapter
import test.createx.dogsimulator.apadters.ListArrayAdapter
import test.createx.dogsimulator.data.models.GridItem
import test.createx.dogsimulator.databinding.FragmentFourthSlideBinding
import test.createx.dogsimulator.databinding.FragmentSimulatorBinding


class SimulatorFragment : Fragment() {

    private lateinit var binding: FragmentSimulatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSimulatorBinding.inflate(inflater, container, false)
        val view = binding.root
        val adapter = GridViewAdapter(requireContext(), setData())
        binding.simulatorGridView.adapter = adapter
        return view
    }

    private fun setData(): ArrayList<GridItem> {
        var gridData: ArrayList<GridItem> = ArrayList();
        gridData.add(GridItem(R.drawable.happy_dog, "Happy"))
        gridData.add(GridItem(R.drawable.neutral_dog, "Neutral"))
        gridData.add(GridItem(R.drawable.smart_dog, "Smart"))
        gridData.add(GridItem(R.drawable.cry_dog, "Cry"))
        gridData.add(GridItem(R.drawable.cute_dog, "Cute"))
        gridData.add(GridItem(R.drawable.love_dog, "Love"))
        gridData.add(GridItem(R.drawable.grumpy_dog, "Grumpy"))
        gridData.add(GridItem(R.drawable.angry_dog, "Angry"))
        gridData.add(GridItem(R.drawable.cool_dog, "Cool"))
        gridData.add(GridItem(R.drawable.festive_dog, "Festive"))
        gridData.add(GridItem(R.drawable.mustache_dog, "Mustache"))
        gridData.add(GridItem(R.drawable.evil_dog, "Evil"))
        gridData.add(GridItem(R.drawable.scared_dog, "Scared"))
        gridData.add(GridItem(R.drawable.sad_dog, "Sad"))
        gridData.add(GridItem(R.drawable.wink_dog, "Wink"))
        gridData.add(GridItem(R.drawable.star_dog, "Star"))
        gridData.add(GridItem(R.drawable.sick_dog, "Sick"))
        gridData.add(GridItem(R.drawable.surprised_dog, "Surprised"))
        gridData.add(GridItem(R.drawable.hupnotized_dog, "Hypnotized"))
        gridData.add(GridItem(R.drawable.tongue_dog, "Tongue"))
        gridData.add(GridItem(R.drawable.fancy_dog, "Fancy"))
        return gridData
    }
}