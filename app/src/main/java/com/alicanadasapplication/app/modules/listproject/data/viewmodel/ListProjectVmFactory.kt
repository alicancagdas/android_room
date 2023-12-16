package com.alicanadasapplication.app.modules.listproject.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.modules.listengineer.data.model.ListEngineerModel
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.ListEngineerVM
import com.alicanadasapplication.app.modules.listproject.data.model.ListProjectModel

class ListProjectVmFactory(private val projectDao: ProjectDao, private val initialModel: ListProjectModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListProjectVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListProjectVM(projectDao, initialModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}