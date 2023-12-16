package com.alicanadasapplication.app.modules.listproject.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.database.entities.Projects
import com.alicanadasapplication.app.modules.listengineer.data.model.ListEngineerModel
import com.alicanadasapplication.app.modules.listproject.`data`.model.ListProjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

class ListProjectVM (private val projectDao: ProjectDao, private val initialModel: ListProjectModel) : ViewModel() {
  val listEngineerModel: MutableLiveData<ListProjectModel> = MutableLiveData(ListProjectModel())
  private val _projelist = MutableLiveData<List<Projects>>()
  private lateinit var listOfProjects: ArrayList<Projects>
  private lateinit var listOfProject: ArrayList<Projects>

  var navArguments: Bundle? = null

  fun getProjects(callbacks:(List<Projects>) -> Unit) {
    listOfProjects = ArrayList()
    viewModelScope.launch(Dispatchers.IO) {
      val projects = projectDao.getAllProjects()
      withContext(Dispatchers.Main) {
        listOfProjects.addAll(projects)
        callbacks(listOfProjects)
      }
    }
  }

  // Add this method to get projects for a specific engineer
  fun getProjectsForEngineer(engineerId: Long, callbacks: (List<Projects>) -> Unit) {
    listOfProject = ArrayList()
    viewModelScope.launch(Dispatchers.IO) {
      val projectsForEngineer = projectDao.getProjectsForEngineer(engineerId)
      withContext(Dispatchers.Main) {
        listOfProject.addAll(projectsForEngineer)
        callbacks(listOfProject)
      }
    }
  }
}