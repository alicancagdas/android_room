package com.alicanadasapplication.app.modules.editengineer.data.model

import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.di.MyApp
import kotlin.String

data class EditEngineerModel(
    /**
     * TODO Replace with dynamic value
     */
    var txtFrameSeventyNine: String? = MyApp.getInstance().resources.getString(R.string.lbl)
    ,
    /**
     * TODO Replace with dynamic value
     */
    var etAddNameValue: String? = null,
    /**
     * TODO Replace with dynamic value
     */
    var etAddSurnameValue: String? = null,
    /**
     * TODO Replace with dynamic value
     */
    var etAddPhoneValue: String? = null,
    /**
     * TODO Replace with dynamic value
     */
    var etAddNoaValue: String? = null
)
