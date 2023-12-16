package com.alicanadasapplication.app.modules.addproject.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.modules.addengineer.data.model.AddEngineerModel
import com.alicanadasapplication.app.modules.addengineer.data.viewmodel.AddEngineerVM
import com.alicanadasapplication.app.modules.addproject.data.model.AddProjectModel

class AddProjectVmFactory(private val projectDao: ProjectDao, private val initialModel: AddProjectModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProjectVM(projectDao, initialModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}