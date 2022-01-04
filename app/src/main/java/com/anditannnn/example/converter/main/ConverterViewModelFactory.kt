package com.anditannnn.example.converter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConverterViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConverterViewModel::class.java)) {
            return ConverterViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}