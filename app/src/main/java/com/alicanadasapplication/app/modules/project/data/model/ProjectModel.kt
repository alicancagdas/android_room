package com.alicanadasapplication.app.modules.project.`data`.model

import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ProjectModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameSeventyNine: String? = MyApp.getInstance().resources.getString(R.string.lbl)

)
