package com.alicanadasapplication.app.modules.editengineer.data.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.modules.editengineer.data.model.EditEngineerModel

class EditEngineerVMFactory(private val muhendisDao: MuhendisDao, private val initialModel: EditEngineerModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditEngineersVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditEngineersVM(muhendisDao, initialModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}