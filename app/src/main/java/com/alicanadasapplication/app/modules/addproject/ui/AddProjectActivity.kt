package com.alicanadasapplication.app.modules.addproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.database.AppDatabase
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.database.entities.Projects
import com.alicanadasapplication.app.databinding.ActivityAddProjectBinding
import com.alicanadasapplication.app.modules.addengineer.data.model.AddEngineerModel
import com.alicanadasapplication.app.modules.addengineer.data.viewmodel.AddEngineerVM
import com.alicanadasapplication.app.modules.addproject.data.model.AddProjectModel
import com.alicanadasapplication.app.modules.addproject.data.viewmodel.ProjectVM
import com.alicanadasapplication.app.modules.addproject.data.viewmodel.AddProjectVmFactory
import com.alicanadasapplication.app.modules.engineer.ui.EngineerActivity
import com.alicanadasapplication.app.modules.project.ui.ProjectActivity
import kotlin.String
import kotlin.Unit

class AddProjectActivity : BaseActivity<ActivityAddProjectBinding>(R.layout.activity_add_project) {
    private val viewModel: ProjectVM by viewModels {
        AddProjectVmFactory(getProjectDao(), AddProjectModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getProjectDao(): ProjectDao {
        val appDatabase = AppDatabase.getDatabase(this)
        return appDatabase.projectDao()
    }


    private fun getEngineerDao(): MuhendisDao {
        val appDatabase = AppDatabase.getDatabase(this)
        return appDatabase.engineerDao()
    }

    override fun onInitialized() {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        binding.addProjectVM = viewModel
    }

    override fun setUpClicks(): Unit {
        binding.txtFrameSeventyNine.setOnClickListener {
            val destIntent = ProjectActivity.getIntent(this, null)
            startActivity(destIntent)
        }

        binding.btnAddProject.setOnClickListener {
            // Veritabanına eklemek için gerekli verileri al
            val title = binding.etPrice.text.toString()
            val managerId = binding.etAddManagerID.text.toString().toLongOrNull() ?: 0L
            val content = binding.etCONTENT.text.toString()
            val project = Projects(title = title, managerId = managerId, content = content)

            viewModel.insertProject(project)
            viewModel.getProjectsAndShowAlertDialog(this)

        }

    }

    companion object {
        const val TAG: String = "ADD_PROJECT_ACTIVITY"


        fun getIntent(context: Context, bundle: Bundle?): Intent {
            val destIntent = Intent(context, AddProjectActivity::class.java)
            destIntent.putExtra("bundle", bundle)
            return destIntent
        }
    }
}
