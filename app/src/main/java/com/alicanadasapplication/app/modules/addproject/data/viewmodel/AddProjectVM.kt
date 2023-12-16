package com.alicanadasapplication.app.modules.addproject.`data`.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicanadasapplication.app.modules.addproject.ui.AddProjectActivity
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.database.entities.Projects
import com.alicanadasapplication.app.modules.addengineer.data.model.AddEngineerModel
import com.alicanadasapplication.app.modules.addproject.`data`.model.AddProjectModel
import com.alicanadasapplication.app.modules.listproject.data.model.ListProjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import com.alicanadasapplication.app.modules.listproject.data.model.ProjectItemModel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ProjectVM(private val projectDao: ProjectDao, private val initialModel: AddProjectModel) : ViewModel() {

  val addProjectModel: MutableLiveData<AddProjectModel> = MutableLiveData(AddProjectModel())

  private val _projectList = MutableLiveData<List<Projects>>()
  val projectList: LiveData<List<Projects>> get() = _projectList

  val txtFrameSeventyNine: MutableLiveData<String> = MutableLiveData()

  var navArguments: Bundle? = null

  fun insertProject(projects: Projects) {
    viewModelScope.launch(Dispatchers.IO) {
      projectDao.insertProject(projects)
    }
  }
  fun getProjectsAndShowAlertDialog(context: Context) {
    viewModelScope.launch(Dispatchers.IO) {
      val projects = projectDao.getAllProjects()
      withContext(Dispatchers.Main) {
        _projectList.postValue(projects)

        // Projeler alındığında bir AlertDialog göster
        showProjectsInAlertDialog(context, projects)
      }
    }
  }

  private fun showProjectsInAlertDialog(context: Context, projects: List<Projects>) {
    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setTitle("Proje Listesi")

    if (projects.isNotEmpty()) {
      val projectsString = buildString {
        for (project in projects) {
          append("Proje ID: ${project.projectId}\n")
          append("Başlık: ${project.title}\n")
          append("Yönetici ID: ${project.managerId}\n")
          append("İçerik: ${project.content}\n\n")
        }
      }
      alertDialogBuilder.setMessage(projectsString)
    } else {
      alertDialogBuilder.setMessage("Proje bulunamadı.")
    }

    alertDialogBuilder.setPositiveButton("Tamam") { dialogInterface: DialogInterface, _: Int ->
      dialogInterface.dismiss()
    }

    val alertDialog = alertDialogBuilder.create()
    alertDialog.show()
  }

  fun deleteProjectById(id: Long) {
    viewModelScope.launch(Dispatchers.IO) {
      projectDao.deleteProjectById(id)
    }
  }

  fun getProjects() {
    viewModelScope.launch(Dispatchers.IO) {
      val projects = projectDao.getAllProjects()
      _projectList.postValue(projects)
    }
  }


}
