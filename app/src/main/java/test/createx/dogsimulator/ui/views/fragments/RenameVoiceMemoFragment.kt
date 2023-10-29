package test.createx.dogsimulator.ui.views.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import test.createx.dogsimulator.R
import test.createx.dogsimulator.utils.FragmentUtils
import java.io.File


class RenameVoiceMemoFragment(
    private val fragmentManager: FragmentManager, private val file: File
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_rename_voice_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavigationView.visibility = View.GONE
        val inputView = view.findViewById<EditText>(R.id.editText)

        val continueButton = view.findViewById<MaterialButton>(R.id.renameButton)
        continueButton.setOnClickListener {
            renameFile(inputView.text.toString())
            FragmentUtils.replaceFragment(
                fragmentManager, VoiceMemosFragment(requireContext(), fragmentManager)
            )
            bottomNavigationView.visibility = View.VISIBLE
            val inputMethodManager =
                requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun renameFile(title: String) {
        val newFilePath = "${file.parent}/${title}.mp3"
        val newFile = File(newFilePath)
        file.renameTo(newFile)
    }
}