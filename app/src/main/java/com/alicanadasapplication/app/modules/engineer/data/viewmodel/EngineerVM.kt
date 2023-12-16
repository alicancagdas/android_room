package com.alicanadasapplication.app.modules.engineer.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicanadasapplication.app.modules.engineer.`data`.model.EngineerModel
import org.koin.core.KoinComponent

class EngineerVM : ViewModel(), KoinComponent {
  val engineerModel: MutableLiveData<EngineerModel> = MutableLiveData(EngineerModel())

  var navArguments: Bundle? = null
}
