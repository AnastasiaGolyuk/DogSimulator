package test.createx.dogsimulator.ui.voice

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import test.createx.dogsimulator.R
import test.createx.dogsimulator.databinding.FragmentRenameVoiceMemoBinding
import test.createx.dogsimulator.utils.FragmentUtils
import java.io.File


class RenameVoiceMemoFragment : Fragment() {

    private lateinit var viewModelFactory: RenameVoiceMemoViewModelFactory
    private lateinit var viewModel: RenameVoiceMemoViewModel
    private lateinit var binding: FragmentRenameVoiceMemoBinding
    private lateinit var file: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRenameVoiceMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModelFactory = RenameVoiceMemoViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(RenameVoiceMemoViewModel::class.java)

        val filePath = arguments?.getString("file_path")
        file = File(filePath!!)

        binding.editText.setText(file.nameWithoutExtension)

        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)

        bottomNavigationView.visibility = View.GONE

        val fragmentManager = requireActivity().supportFragmentManager

        binding.renameButton.setOnClickListener {
            renameFile(binding.editText.text.toString())
            FragmentUtils.replaceFragment(
                fragmentManager, VoiceMemosFragment()
            )
            bottomNavigationView.visibility = View.VISIBLE
            val inputMethodManager =
                requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun renameFile(title: String) {
        val filePath = arguments?.getString("file_path")
        val file = File(filePath!!)
        val newFilePath = "${file.parent}/${title}.mp3"
        val newFile = File(newFilePath)
        file.renameTo(newFile)
    }
}