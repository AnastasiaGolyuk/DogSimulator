package test.createx.dogsimulator.ui.translator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import test.createx.dogsimulator.databinding.FragmentTranslatorBinding


class TranslatorFragment : Fragment() {

    private lateinit var binding: FragmentTranslatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.root
    }

}