package test.createx.dogsimulator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import test.createx.dogsimulator.apadters.SubscriptionListAdapter
import test.createx.dogsimulator.databinding.FragmentFourthSlideBinding


class FourthSlideFragment : Fragment() {

    private lateinit var viewModel: FourthSlideViewModel
    private lateinit var binding: FragmentFourthSlideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthSlideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FourthSlideViewModel::class.java)

        val subscriptionListView = binding.sliderSubscriptionList
        subscriptionListView.layoutManager = LinearLayoutManager(context)
        val adapter = SubscriptionListAdapter()
        subscriptionListView.adapter = adapter

        viewModel.subscriptionList.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.disconnectClient()
    }
}