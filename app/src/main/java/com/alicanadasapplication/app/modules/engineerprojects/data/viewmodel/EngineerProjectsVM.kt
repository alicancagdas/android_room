package com.alicanadasapplication.app.modules.engineerprojects.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicanadasapplication.app.modules.engineerprojects.`data`.model.EngineerProjectsModel
import org.koin.core.KoinComponent

class EngineerProjectsVM : ViewModel(), KoinComponent {
  val engineerProjectsModel: MutableLiveData<EngineerProjectsModel> =
      MutableLiveData(EngineerProjectsModel())

  var navArguments: Bundle? = null
}
