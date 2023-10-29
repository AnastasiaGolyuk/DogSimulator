package test.createx.dogsimulator.ui.views.fragments

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import test.createx.dogsimulator.R
import test.createx.dogsimulator.apadters.GridViewAdapter
import test.createx.dogsimulator.data.models.GridItem


class SimulatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simulator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.INTERNET),
            0
        )
        val adapter = GridViewAdapter(requireContext(), setData())
        val simulatorGridView = view.findViewById<GridView>(R.id.simulatorGridView)
        simulatorGridView.adapter = adapter
    }

    private fun setData(): ArrayList<GridItem> {
        val gridData: ArrayList<GridItem> = ArrayList()
        gridData.add(
            GridItem(
                R.drawable.happy_dog,
                "Happy",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-1.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.neutral_dog,
                "Neutral",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-2.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.smart_dog,
                "Smart",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-3.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.cry_dog,
                "Cry",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-4.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.cute_dog,
                "Cute",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-10.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.love_dog,
                "Love",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-11.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.grumpy_dog,
                "Grumpy",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-12.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.angry_dog,
                "Angry",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-9.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.cool_dog,
                "Cool",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-7.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.festive_dog,
                "Festive",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-8.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.mustache_dog,
                "Mustache",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-6.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.evil_dog,
                "Evil",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-5.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.scared_dog,
                "Scared",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-11.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.sad_dog,
                "Sad",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-1.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.wink_dog,
                "Wink",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-10.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.star_dog,
                "Star",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-7.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.sick_dog,
                "Sick",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-3.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.surprised_dog,
                "Surprised",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-4.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.hupnotized_dog,
                "Hypnotized",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-12.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.tongue_dog,
                "Tongue",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-9.wav"
            )
        )
        gridData.add(
            GridItem(
                R.drawable.fancy_dog,
                "Fancy",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-2.wav"
            )
        )
        return gridData
    }
}