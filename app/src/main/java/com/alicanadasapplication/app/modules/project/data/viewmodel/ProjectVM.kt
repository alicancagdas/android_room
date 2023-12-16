package com.alicanadasapplication.app.modules.project.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicanadasapplication.app.modules.project.`data`.model.ProjectModel
import org.koin.core.KoinComponent

class ProjectVM : ViewModel(), KoinComponent {
  val projectModel: MutableLiveData<ProjectModel> = MutableLiveData(ProjectModel())

  var navArguments: Bundle? = null
}
