package com.alicanadasapplication.app.modules.addproject.`data`.model

import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class AddProjectModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameSeventyNine: String? = MyApp.getInstance().resources.getString(R.string.lbl)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etPriceValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etAddManagerIDValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etCONTENTValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_content)

)
