package com.alicanadasapplication.app.modules.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.databinding.ActivityMainBinding
import com.alicanadasapplication.app.modules.engineer.ui.EngineerActivity
import com.alicanadasapplication.app.modules.main.`data`.viewmodel.MainVM
import com.alicanadasapplication.app.modules.project.ui.ProjectActivity
import kotlin.String
import kotlin.Unit

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
  private val viewModel: MainVM by viewModels<MainVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.mainVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnProjects.setOnClickListener {
      val destIntent = ProjectActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnEngineers.setOnClickListener {
      val destIntent = EngineerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "MAIN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MainActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
