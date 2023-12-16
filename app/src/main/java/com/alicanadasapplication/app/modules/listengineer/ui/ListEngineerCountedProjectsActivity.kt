package com.alicanadasapplication.app.modules.listengineer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.database.AppDatabase
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.databinding.ActivityListEngineerBinding
import com.alicanadasapplication.app.modules.listengineer.data.model.ListEngineerModel
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.EngineerListAdapter
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.ListEngineerVM
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.ListEngineerVmFactory
import com.alicanadasapplication.app.modules.main.ui.MainActivity

class ListEngineerCountedProjectsActivity:  BaseActivity<ActivityListEngineerBinding>(R.layout.activity_list_engineer) {

    private lateinit var adapter: EngineerListAdapter
    private val viewModel: ListEngineerVM by viewModels {
        ListEngineerVmFactory(getMuhendisDao(), ListEngineerModel())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.listEngineerRecyclerView.setHasFixedSize(true)
        binding.listEngineerRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getCountThreeEngineers { engineers ->
            Log.e("data",engineers.size.toString())
            adapter = EngineerListAdapter(this@ListEngineerCountedProjectsActivity,engineers)
            binding.listEngineerRecyclerView.adapter = adapter
        }
    }

    private fun getMuhendisDao(): MuhendisDao {
        val appDatabase = AppDatabase.getDatabase(this)
        return appDatabase.engineerDao()
    }


    override fun onInitialized() {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        binding.listEngineerVM = viewModel
    }


    override fun setUpClicks(): Unit {

        binding.txtFrameSeventyNine.setOnClickListener {
            val destIntent = MainActivity.getIntent(this, null)
            startActivity(destIntent)
        }
    }

    companion object {
        const val TAG: String = "LIST_ENGINEER_ACTIVITY"


        fun getIntent(context: Context, bundle: Bundle?): Intent {
            val destIntent = Intent(context, ListEngineerActivity::class.java)
            destIntent.putExtra("bundle", bundle)
            return destIntent
        }
    }
}
