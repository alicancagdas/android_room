package com.alicanadasapplication.app.modules.listproject.`data`.model

import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ListProjectModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameSeventyNine: String? = MyApp.getInstance().resources.getString(R.string.lbl)

)
