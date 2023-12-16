package com.alicanadasapplication.app.modules.project.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.databinding.ActivityProjectBinding
import com.alicanadasapplication.app.modules.addproject.ui.AddProjectActivity
import com.alicanadasapplication.app.modules.listproject.ui.ListProjectActivity
import com.alicanadasapplication.app.modules.main.ui.MainActivity
import com.alicanadasapplication.app.modules.project.`data`.viewmodel.ProjectVM
import kotlin.String
import kotlin.Unit

class ProjectActivity : BaseActivity<ActivityProjectBinding>(R.layout.activity_project) {
  private val viewModel: ProjectVM by viewModels<ProjectVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.projectVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtFrameSeventyNine.setOnClickListener {
      val destIntent = MainActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnListProject.setOnClickListener {
      val destIntent = ListProjectActivity.getIntent(this,null)
      startActivity(destIntent)
    }
    binding.btnAddNewProjectOne.setOnClickListener {
      val destIntent = AddProjectActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PROJECT_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ProjectActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
