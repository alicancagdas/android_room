package com.alicanadasapplication.app.modules.engineerprojects.`data`.model

import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class EngineerProjectsModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtENGINNERXPROJ: String? =
      MyApp.getInstance().resources.getString(R.string.msg_enginner_x_proj)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPrice: String? = MyApp.getInstance().resources.getString(R.string.lbl_title)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMANAGERID: String? = MyApp.getInstance().resources.getString(R.string.lbl_manager_id2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCONTENT: String? = MyApp.getInstance().resources.getString(R.string.lbl_content)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameEighty: String? = MyApp.getInstance().resources.getString(R.string.lbl)

)
