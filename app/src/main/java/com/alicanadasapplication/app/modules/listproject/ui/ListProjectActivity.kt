package com.alicanadasapplication.app.modules.listproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.database.AppDatabase
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.databinding.ActivityListProjectBinding
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.EngineerListAdapter
import com.alicanadasapplication.app.modules.listproject.data.model.ListProjectModel
import com.alicanadasapplication.app.modules.listproject.`data`.viewmodel.ListProjectVM
import com.alicanadasapplication.app.modules.listproject.data.viewmodel.ListProjectVmFactory
import com.alicanadasapplication.app.modules.listproject.data.viewmodel.ProjectListAdapter
import com.alicanadasapplication.app.modules.main.ui.MainActivity
import kotlin.String
import kotlin.Unit

class ListProjectActivity: BaseActivity<ActivityListProjectBinding>(R.layout.activity_list_project) {

  private lateinit var adapter: ProjectListAdapter
  private val viewModel: ListProjectVM by viewModels {
    ListProjectVmFactory(getProjectDao(), ListProjectModel())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.listProjectRecyclerView.setHasFixedSize(true)
    binding.listProjectRecyclerView.layoutManager = LinearLayoutManager(this)

    // Get the data type from the intent extras

    // Use the data type to determine the behavior
    val engineerId = intent.getLongExtra("engineerId", -1)
    Log.d("idkontrolediyorum", "${engineerId}")


    // Use the engineerId to determine the behavior
    if (engineerId != -1L) {
      // Case: Projects for a specific engineer
      viewModel.getProjectsForEngineer(engineerId) { projects ->
        Log.e("data", projects.size.toString())
        adapter = ProjectListAdapter(this@ListProjectActivity, projects)
        binding.listProjectRecyclerView.adapter = adapter
      }
    } else {
      // Case: All projects (engineerId not provided)
      viewModel.getProjects { projects ->
        Log.e("data", projects.size.toString())
        adapter = ProjectListAdapter(this@ListProjectActivity, projects)
        binding.listProjectRecyclerView.adapter = adapter
      }
    }
    }



  private fun getProjectDao(): ProjectDao {
    val appDatabase = AppDatabase.getDatabase(this)
    return appDatabase.projectDao()
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
    const val TAG: String = "LIST_PROJECT_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ListProjectActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
