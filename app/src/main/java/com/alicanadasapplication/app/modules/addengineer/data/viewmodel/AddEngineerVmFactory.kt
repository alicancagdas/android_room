package com.alicanadasapplication.app.modules.addengineer.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.modules.addengineer.data.model.AddEngineerModel


class AddEngineerVmFactory(private val muhendisDao: MuhendisDao, private val initialModel: AddEngineerModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEngineerVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddEngineerVM(muhendisDao, initialModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}