package test.createx.dogsimulator.ui.simulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import test.createx.dogsimulator.apadters.SimulatorGridAdapter
import test.createx.dogsimulator.databinding.FragmentSimulatorBinding


class SimulatorFragment : Fragment() {

    private lateinit var viewModelFactory: SimulatorViewModelFactory
    private lateinit var viewModel: SimulatorViewModel
    private lateinit var binding: FragmentSimulatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSimulatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelFactory = SimulatorViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SimulatorViewModel::class.java)

        val simulatorGridView = binding.simulatorGridView
        simulatorGridView.layoutManager = GridLayoutManager(context,3)
        val adapter = SimulatorGridAdapter()
        simulatorGridView.adapter = adapter

        viewModel.dogEmotionsList.observe(viewLifecycleOwner) { items ->
            adapter.setItems(items)
        }
    }
}