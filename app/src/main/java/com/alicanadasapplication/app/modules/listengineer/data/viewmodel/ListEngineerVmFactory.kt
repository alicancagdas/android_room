package com.alicanadasapplication.app.modules.listengineer.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicanadasapplication.app.database.dao.MuhendisDao

import com.alicanadasapplication.app.modules.listengineer.data.model.ListEngineerModel

class ListEngineerVmFactory(private val muhendisDao: MuhendisDao, private val initialModel: ListEngineerModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListEngineerVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListEngineerVM(muhendisDao, initialModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}