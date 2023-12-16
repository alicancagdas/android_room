package com.alicanadasapplication.app.modules.listengineer.`data`.model

import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ListEngineerModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtEngineerID: String? = MyApp.getInstance().resources.getString(R.string.lbl_engineerid)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSurname: String? = MyApp.getInstance().resources.getString(R.string.lbl_surname2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhone: String? = MyApp.getInstance().resources.getString(R.string.lbl_phone2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNop: String? = MyApp.getInstance().resources.getString(R.string.msg_number_of_proje)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNames: String? = MyApp.getInstance().resources.getString(R.string.lbl_name2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameSeventyNine: String? = MyApp.getInstance().resources.getString(R.string.lbl)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEngineerList: String? = MyApp.getInstance().resources.getString(R.string.lbl_name2)

)