package com.alicanadasapplication.app.modules.engineer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.databinding.ActivityEngineerBinding
import com.alicanadasapplication.app.modules.addengineer.ui.AddEngineerActivity
import com.alicanadasapplication.app.modules.engineer.`data`.viewmodel.EngineerVM
import com.alicanadasapplication.app.modules.listengineer.ui.ListEngineerActivity
import com.alicanadasapplication.app.modules.main.ui.MainActivity
import kotlin.String
import kotlin.Unit

class EngineerActivity : BaseActivity<ActivityEngineerBinding>(R.layout.activity_engineer) {
  private val viewModel: EngineerVM by viewModels<EngineerVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.engineerVM = viewModel
  }
  override fun setUpClicks(): Unit {
    binding.btnListEngineer.setOnClickListener {
      val destIntent = ListEngineerActivity.getIntent(this, null, "allEngineers")
      startActivity(destIntent)
    }
    binding.btnAddNewEngineerOne.setOnClickListener {
      val destIntent = AddEngineerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnListCountThree.setOnClickListener {
      val destIntent = ListEngineerActivity.getIntent(this, null, "countedProjects")
      startActivity(destIntent)
    }
    // Other button click handlers...
    binding.txtFrameSeventyNine.setOnClickListener {
      val destIntent = MainActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "ENGINEER_ACTIVITY"
    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, EngineerActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
