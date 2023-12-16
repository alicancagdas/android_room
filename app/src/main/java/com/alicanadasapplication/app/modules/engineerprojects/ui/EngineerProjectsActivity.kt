package com.alicanadasapplication.app.modules.engineerprojects.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.databinding.ActivityEngineerProjectsBinding
import com.alicanadasapplication.app.modules.engineerprojects.`data`.viewmodel.EngineerProjectsVM
import com.alicanadasapplication.app.modules.listengineer.ui.ListEngineerActivity
import kotlin.String
import kotlin.Unit

class EngineerProjectsActivity :
    BaseActivity<ActivityEngineerProjectsBinding>(R.layout.activity_engineer_projects) {
  private val viewModel: EngineerProjectsVM by viewModels<EngineerProjectsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.engineerProjectsVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtFrameEighty.setOnClickListener {
    }
  }

  companion object {
    const val TAG: String = "ENGINEER_PROJECTS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, EngineerProjectsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
