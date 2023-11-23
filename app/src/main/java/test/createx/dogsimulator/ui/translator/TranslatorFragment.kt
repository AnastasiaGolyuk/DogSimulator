package test.createx.dogsimulator.ui.translator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButtonToggleGroup
import test.createx.dogsimulator.R
import test.createx.dogsimulator.databinding.FragmentTranslatorBinding


class TranslatorFragment : Fragment() {

    private lateinit var binding: FragmentTranslatorBinding
    private lateinit var viewModelFactory: TranslatorViewModelFactory
    private lateinit var viewModel: TranslatorViewModel
    private var originalMode: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = TranslatorViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(TranslatorViewModel::class.java)

        originalMode = requireActivity().window?.attributes?.softInputMode

        requireActivity().window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )

        val toggleGroup: MaterialButtonToggleGroup =
            requireActivity().findViewById(R.id.toggleGroup)

        val checkedButtonId = toggleGroup.checkedButtonId

        showLayout(checkedButtonId)

        toggleGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if (isChecked) {
                viewModel.setId(checkedId)
            }
        }

        viewModel.checkedId.observe(viewLifecycleOwner) { id ->
            showLayout(id)
        }

        var isRecording = false

        binding.startRecordingButton.setOnClickListener {
            if (!isRecording) {
                binding.startRecordingButton.setIconResource(R.drawable.square_stop_84)
                binding.translateButton.setIconResource(R.drawable.square_micro_icon)
                isRecording = true
            } else {
                isRecording = false
                binding.translationLayout.visibility = View.VISIBLE
                binding.startRecordingButton.visibility = View.INVISIBLE
                binding.editText.setText("test text")
                binding.translateButton.setIconResource(R.drawable.square_retry_button)
                binding.startRecordingButton.setIconResource(R.drawable.micro_54)
            }
        }

        binding.translateButton.setOnClickListener {
            if (viewModel.checkedId.value == R.id.toggleDogButton) {
                binding.translationLayout.visibility = View.INVISIBLE
                binding.startRecordingButton.visibility = View.VISIBLE
            } else {
                if (binding.editText.text.isNotEmpty()) {
                    viewModel.togglePlayer()
                }
            }
        }

        viewModel.isPlaying.observe(viewLifecycleOwner) { isPlaying ->
            if (isPlaying) {
                viewModel.playSound(
                    "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-4.wav"
                ) {
                    viewModel.togglePlayer()
                }
                binding.translateButton.setIconResource(R.drawable.square_stop_icon)
            } else {
                viewModel.stopPlayer()
                binding.translateButton.setIconResource(R.drawable.square_retry_button)
            }
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                binding.translateButton.setIconResource(R.drawable.square_micro_icon)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        originalMode?.let { activity?.window?.setSoftInputMode(it) }
        viewModel.releasePlayer()
    }

    private fun showLayout(checkedId: Int) {
        if (checkedId == R.id.toggleDogButton) {
            binding.translationLayout.visibility = View.INVISIBLE
            binding.startRecordingButton.visibility = View.VISIBLE
        } else {
            binding.translationLayout.visibility = View.VISIBLE
            binding.startRecordingButton.visibility = View.INVISIBLE
            binding.editText.setText("")
            binding.translateButton.setIconResource(R.drawable.square_micro_icon)
        }
    }
}