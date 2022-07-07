package com.flores.dummydictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.flores.dummydictionary.DummyDictionaryApplication
import com.flores.dummydictionary.R
import com.flores.dummydictionary.data.model.Word
import com.flores.dummydictionary.databinding.FragmentAddWordBinding

class AddWordFragment : Fragment() {

    private val viewModelFactory by lazy {
        val application = requireActivity().application as DummyDictionaryApplication
        ViewModelFactory(application.getDictionaryRepository())
    }

    private val viewModel: WordViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentAddWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener{
            viewModel.addWord( Word(binding.nameEditText2.text.toString(), binding.definitionEditText2.text.toString(), false ))
            findNavController()
                .navigate(R.id.wordListFragment)
        }
    }
}