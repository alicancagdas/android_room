package com.alicanadasapplication.app.modules.main.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicanadasapplication.app.modules.main.`data`.model.MainModel
import org.koin.core.KoinComponent

class MainVM : ViewModel(), KoinComponent {
  val mainModel: MutableLiveData<MainModel> = MutableLiveData(MainModel())

  var navArguments: Bundle? = null
}
