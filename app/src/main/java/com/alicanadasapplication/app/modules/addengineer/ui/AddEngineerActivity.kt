package com.alicanadasapplication.app.modules.addengineer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.appcomponents.base.BaseActivity
import com.alicanadasapplication.app.database.AppDatabase
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.databinding.ActivityAddEngineerBinding
import com.alicanadasapplication.app.modules.addengineer.data.model.AddEngineerModel
import com.alicanadasapplication.app.modules.addengineer.`data`.viewmodel.AddEngineerVM
import com.alicanadasapplication.app.modules.addengineer.data.viewmodel.AddEngineerVmFactory
import com.alicanadasapplication.app.modules.engineer.ui.EngineerActivity
import kotlin.String

class AddEngineerActivity : BaseActivity<ActivityAddEngineerBinding>(R.layout.activity_add_engineer) {

  private val viewModel: AddEngineerVM by viewModels {
    AddEngineerVmFactory(getMuhendisDao(), AddEngineerModel())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Diğer işlemleri burada yapın
  }

  private fun getMuhendisDao(): MuhendisDao {
    val appDatabase = AppDatabase.getDatabase(this)
    return appDatabase.engineerDao()
  }

  override fun onInitialized() {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.addEngineerVM = viewModel
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.addEngineerVM = viewModel

    // Check if the intent has an "engineerId" extra to determine the source
    val engineerId = intent.getLongExtra("engineerId", -1)
    if (engineerId.toInt() != -1) {
      // This is coming from EngineerListAdapter, hide unnecessary buttons
      binding.btnAddEngineers.visibility = View.GONE
      binding.btnEditEngineer.visibility = View.VISIBLE
      binding.btnDeleteEngineer.visibility = View.VISIBLE
    } else {
      // This is a new engineer, hide unnecessary buttons
      binding.btnAddEngineers.visibility = View.VISIBLE
      binding.btnEditEngineer.visibility = View.GONE
      binding.btnDeleteEngineer.visibility = View.GONE
    }

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

      // Veritabanına muhendis eklemek için viewModel'deki fonksiyonu çağır
      viewModel.insertEngineer(muhendis)
      viewModel.getProjectsAndShowAlertDialog(this)
    }

    binding.btnEditEngineer.setOnClickListener {
      // Veritabanına eklemek için gerekli verileri al
      val name = binding.etAddName.text.toString()
      val surname = binding.etAddSurname.text.toString()
      val phone = binding.etAddPhone.text.toString()
      val noa = binding.etAddNoa.text.toString()
      // project counter integer
      val noai = if (noa.isNotEmpty()) noa.toInt() else 0

      // Muhendisler sınıfından bir nesne oluştur
      val muhendis = Muhendisler(name = name, surname = surname, phone = phone, numberOfProjects = noai)

      // Veritabanına muhendis eklemek için viewModel'deki fonksiyonu çağır
      val engineerId = intent.getLongExtra("engineerId", -1)
      // Veritabanına muhendis eklemek için viewModel'deki fonksiyonu çağır
      viewModel.updateEngineer(engineerId , muhendis)

      viewModel.getProjectsAndShowAlertDialog(this)
    }
    binding.txtFrameSeventyNine.setOnClickListener{
      // Ekledikten sonra başka bir ekrana geçiş yapabilirsin
      val destIntent = EngineerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnDeleteEngineer.setOnClickListener {

      val engineerId = intent.getLongExtra("engineerId", -1)
      // Veritabanına muhendis eklemek için viewModel'deki fonksiyonu çağır
      viewModel.deleteEngineer(engineerId)
      viewModel.getProjectsAndShowAlertDialog(this)
    }
  }

  companion object {
    const val TAG: String = "ADD_ENGINEER_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AddEngineerActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
