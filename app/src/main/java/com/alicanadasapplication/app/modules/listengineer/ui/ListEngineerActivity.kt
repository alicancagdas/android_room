package com.alicanadasapplication.app.modules.listengineer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.database.AppDatabase
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.databinding.ActivityListEngineerBinding
import com.alicanadasapplication.app.modules.addproject.data.model.AddProjectModel
import com.alicanadasapplication.app.modules.addproject.data.viewmodel.AddProjectVmFactory
import com.alicanadasapplication.app.modules.addproject.data.viewmodel.ProjectVM
import com.alicanadasapplication.app.modules.engineer.ui.EngineerActivity
import com.alicanadasapplication.app.modules.engineerprojects.ui.EngineerProjectsActivity
import com.alicanadasapplication.app.modules.listengineer.data.model.ListEngineerModel
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.EngineerListAdapter
import com.alicanadasapplication.app.modules.listengineer.`data`.viewmodel.ListEngineerVM
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.ListEngineerVmFactory
import com.alicanadasapplication.app.modules.listproject.data.model.ListProjectModel
import com.alicanadasapplication.app.modules.main.ui.MainActivity
import kotlin.String
import kotlin.Unit

class ListEngineerActivity :
  BaseActivity<ActivityListEngineerBinding>(R.layout.activity_list_engineer) {

  private lateinit var adapter: EngineerListAdapter
  private val viewModel: ListEngineerVM by viewModels {
    ListEngineerVmFactory(getMuhendisDao(), ListEngineerModel())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.listEngineerRecyclerView.setHasFixedSize(true)
    binding.listEngineerRecyclerView.layoutManager = LinearLayoutManager(this)

    // Get the data type from the intent extras
    val dataType = intent.getStringExtra("dataType")

    // Use the data type to determine the behavior
    when (dataType) {
      "allEngineers" -> {
        viewModel.getEngineers { engineers ->
          Log.e("data", engineers.size.toString())
          adapter = EngineerListAdapter(this@ListEngineerActivity, engineers)
          binding.listEngineerRecyclerView.adapter = adapter
        }
      }
      "countedProjects" -> {
        viewModel.getCountThreeEngineers { engineers ->
          Log.e("data", engineers.size.toString())
          adapter = EngineerListAdapter(this@ListEngineerActivity, engineers)
          binding.listEngineerRecyclerView.adapter = adapter
        }
      }
      // Add more cases for different data types if needed

      // Default behavior (if no or unknown data type provided)
      else -> {
        // Handle default behavior or show an error message
      }
    }
  }

  private fun getMuhendisDao(): MuhendisDao {
    val appDatabase = AppDatabase.getDatabase(this)
    return appDatabase.engineerDao()
  }


  override fun onInitialized() {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
  }


  override fun setUpClicks(): Unit {

    binding.txtFrameSeventyNine.setOnClickListener {
      val destIntent = MainActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }
  companion object {
    const val TAG: String = "LIST_ENGINEER_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?, dataType: String): Intent {
      val destIntent = Intent(context, ListEngineerActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      destIntent.putExtra("dataType", dataType)
      return destIntent
    }
  }
}
