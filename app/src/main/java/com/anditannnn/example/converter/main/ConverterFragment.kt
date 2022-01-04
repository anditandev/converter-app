package com.anditannnn.example.converter.main

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.text.TextWatcher
import com.anditannnn.example.converter.R
import com.anditannnn.example.converter.databinding.FragmentConverterBinding

enum class TEMPERATURE {
    CELSIUS, FAHRENHEIT, KELVIN
}

class ConverterFragment : Fragment() {

    private lateinit var viewModel: ConverterViewModel
    private lateinit var viewModelFactory: ConverterViewModelFactory
    private var focusOwner: TEMPERATURE = TEMPERATURE.CELSIUS

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentConverterBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_converter,
            container,
            false
        )

        viewModelFactory = ConverterViewModelFactory()
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(ConverterViewModel::class.java)

        binding.converterViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.celsiusTextInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) focusOwner = TEMPERATURE.CELSIUS }
        binding.fahrenheitTextInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) focusOwner = TEMPERATURE.FAHRENHEIT }
        binding.kelvinTextInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) focusOwner = TEMPERATURE.KELVIN}

        binding.celsiusTextInput.addTextChangedListener( object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                s.toString().toDoubleOrNull()
                if (focusOwner == TEMPERATURE.CELSIUS)
                    if (viewModel.checkRegex(s.toString())) {

                        if (binding.fahrenheitTextLayout.error != null)
                            binding.fahrenheitTextLayout.error = null
                        if (binding.kelvinTextLayout.error != null)
                            binding.kelvinTextLayout.error = null

                        binding.celsiusTextLayout.error = null
                        viewModel.convertCelsius(s.toString())
                    }
                    else
                        binding.celsiusTextLayout.error = getString(R.string.text_field_error_string)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.fahrenheitTextInput.addTextChangedListener( object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (focusOwner == TEMPERATURE.FAHRENHEIT)
                    if (viewModel.checkRegex(s.toString())) {

                        if (binding.celsiusTextLayout.error != null)
                            binding.celsiusTextLayout.error = null
                        if (binding.kelvinTextLayout.error != null)
                            binding.kelvinTextLayout.error = null

                        binding.fahrenheitTextLayout.error = null
                        viewModel.convertFahrenheit(s.toString())
                    }
                    else
                        binding.fahrenheitTextLayout.error = getString(R.string.text_field_error_string)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.kelvinTextInput.addTextChangedListener( object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (focusOwner == TEMPERATURE.KELVIN)
                    if (viewModel.checkRegex(s.toString())) {

                        if (binding.fahrenheitTextLayout.error != null)
                            binding.fahrenheitTextLayout.error = null
                        if (binding.celsiusTextLayout.error != null)
                            binding.celsiusTextLayout.error = null

                        binding.kelvinTextLayout.error = null
                        viewModel.convertKelvin(s.toString())
                    }
                    else
                        binding.kelvinTextLayout.error = getString(R.string.text_field_error_string)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        return binding.root
    }

}