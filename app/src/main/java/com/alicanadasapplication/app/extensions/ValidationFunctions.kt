package com.alicanadasapplication.app.extensions

import android.telephony.PhoneNumberUtils
import android.widget.Toast
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.di.MyApp
import kotlin.Boolean
import kotlin.String

public fun String?.isText(isMandatory: Boolean = false): Boolean {
  if (isNullOrEmpty()) {
    if (isMandatory) {
      Toast.makeText(
              MyApp.getInstance(),
              R.string.msg_please_enter_valid_text,
              Toast.LENGTH_SHORT
          ).show()
    }
    return !isMandatory
  }
  val textRegex = "[a-zA-Z ]+".toRegex()
  val result = 
  textRegex.matches(this)
  if (!result) {
    Toast.makeText(
            MyApp.getInstance(),
            R.string.msg_please_enter_valid_text,
            Toast.LENGTH_SHORT
        ).show()
  }
  return result
}

public fun String?.isValidPhone(isMandatory: Boolean = false): Boolean {
  if (isNullOrEmpty()) {
    if (isMandatory) {
      Toast.makeText(
              MyApp.getInstance(),
              R.string.msg_please_enter_valid_phone_nu,
              Toast.LENGTH_SHORT
          ).show()
    }
    return !isMandatory
  }
  val result = 
  PhoneNumberUtils.isGlobalPhoneNumber(this)
  if (!result) {
    Toast.makeText(
            MyApp.getInstance(),
            R.string.msg_please_enter_valid_phone_nu,
            Toast.LENGTH_SHORT
        ).show()
  }
  return result
}
