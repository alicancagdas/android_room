package com.alicanadasapplication.app.modules.editengineer.data.viewmodel

import android.os.Bundle
import com.alicanadasapplication.app.databinding.ActivityEditEngineerBinding
import com.alicanadasapplication.app.modules.editengineer.data.model.EditEngineerModel


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.database.AppDatabase
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.modules.engineer.ui.EngineerActivity
import kotlin.String
import kotlin.math.log

class EditEngineerActivity : BaseActivity<ActivityEditEngineerBinding>(R.layout.activity_edit_engineer) {

    private val viewModel: EditEngineersVM by viewModels {
        EditEngineerVMFactory(getMuhendisDao(), EditEngineerModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_engineer)

        // Retrieve engineerId from the intent
        val engineerId = intent.getLongExtra("engineerId", -1)

        // Now you can use the engineerId as needed
        if (engineerId.toInt() != -1) {
            // Do something with the engineerId
            // For example, log it or use it to fetch data from the database
            Log.d("logmesajiister", "Received engineerId: $engineerId")
        } else {
            // Handle the case where engineerId is not found in the intent
            Log.e("logmesajiister", "No engineerId found in the intent")
            // You might want to finish the activity or show an error message
        }
    }


    private fun getMuhendisDao(): MuhendisDao {
        val appDatabase = AppDatabase.getDatabase(this)
        return appDatabase.engineerDao()
    }

    override fun onInitialized() {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        binding.editEngineersVM = viewModel
    }

    override fun setUpClicks() {
        binding.btnAddEngineers.setOnClickListener {
            // Veritabanına eklemek için gerekli verileri al
            val name = binding.etAddName.text.toString()
            val surname = binding.etAddSurname.text.toString()
            val phone = binding.etAddPhone.text.toString()
            val noa = binding.etAddNoa.text.toString()
            // project counter integer
            val noai = if (noa.isNotEmpty()) noa.toInt() else 0

            // Muhendisler sınıfından bir nesne oluştur
            val muhendis = Muhendisler(name = name, surname = surname, phone = phone, numberOfProjects = noai)

            val engineerId = intent.getLongExtra("engineerId", -1)
            // Veritabanına muhendis eklemek için viewModel'deki fonksiyonu çağır
            viewModel.updateEngineer(engineerId , muhendis)
            viewModel.getProjectsAndShowAlertDialog(this)
        }
        binding.txtFrameSeventyNine.setOnClickListener{
            // Ekledikten sonra başka bir ekrana geçiş yapabilirsin
            val destIntent = EngineerActivity.getIntent(this, null)
            startActivity(destIntent)
        }}
    companion object {
        const val TAG: String = "EDIT_ENGINEER_ACTIVITY"

        fun getIntent(context: Context, bundle: Bundle?): Intent {
            val destIntent = Intent(context, EditEngineersVM::class.java)
            destIntent.putExtra("bundle", bundle)
            return destIntent
        }
    }
}
