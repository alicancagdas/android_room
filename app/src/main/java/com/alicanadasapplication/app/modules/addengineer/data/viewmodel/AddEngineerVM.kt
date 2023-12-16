package com.alicanadasapplication.app.modules.addengineer.`data`.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.database.entities.Projects
import com.alicanadasapplication.app.modules.addengineer.`data`.model.AddEngineerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent


class AddEngineerVM(private val muhendisDao: MuhendisDao, private val initialModel: AddEngineerModel) : ViewModel() {

  val addEngineerModel: MutableLiveData<AddEngineerModel> = MutableLiveData(AddEngineerModel())
  var navArguments: Bundle? = null
  private val _muhendisList = MutableLiveData<List<Muhendisler>>()

  suspend fun getEngineerById(engineerId: Long): Muhendisler {
    return withContext(Dispatchers.IO) {
      muhendisDao.getEngineerById(engineerId)
    }
  }

  fun updateEngineer(engineerId: Long, updatedEngineer: Muhendisler) {
    viewModelScope.launch(Dispatchers.IO) {
      // Retrieve the existing engineer from the database
      val existingEngineer = getEngineerById(engineerId)

      // Check if the existing engineer is not null
      if (existingEngineer != null) {
        Log.d("existingengineer","${existingEngineer}")
        // Update the fields of the existing engineer with the new data
        existingEngineer.name = updatedEngineer.name
        existingEngineer.surname = updatedEngineer.surname
        existingEngineer.phone = updatedEngineer.phone
        existingEngineer.numberOfProjects = updatedEngineer.numberOfProjects

        // Update the engineer in the database
        muhendisDao.updateEngineer(existingEngineer)
      } else {
        // Handle the case where the engineer with the specified ID is not found
        Log.e("EditEngineersVM", "Engineer not found for ID: $engineerId")
      }
    }
  }

  fun insertEngineer(muhendis: Muhendisler) {
    viewModelScope.launch(Dispatchers.IO) {
      muhendisDao.insertMuhendis(muhendis)
    }
  }
  fun getProjectsAndShowAlertDialog(context: Context) {
    viewModelScope.launch(Dispatchers.IO) {
      val muhendisler = muhendisDao.getAllMuhendis()
      withContext(Dispatchers.Main) {
        _muhendisList.postValue(muhendisler)

        // Projeler alındığında bir AlertDialog göster
        showProjectsInAlertDialog(context, muhendisler)
      }
    }
  }
  fun showProjectsInAlertDialog(context: Context, muhendisler: List<Muhendisler>) {
    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setTitle("Proje Listesi")

    if (muhendisler.isNotEmpty()) {
      val muhendisString = buildString {
        for (muhendis in muhendisler) {
          append("Muhendis ID: ${muhendis.engineerId}\n")
          append("İsim: ${muhendis.name}\n")
          append("Soyisim: ${muhendis.surname}\n")
          append("Telefon Numarasi: ${muhendis.phone}\n")
          append("Proje Sayısı: ${muhendis.numberOfProjects}\n\n")
        }
      }
      alertDialogBuilder.setMessage(muhendisString)
    } else {
      alertDialogBuilder.setMessage("Muhendis bulunamadı.")
    }

    alertDialogBuilder.setPositiveButton("Tamam") { dialogInterface: DialogInterface, _: Int ->
      dialogInterface.dismiss()
    }

    val alertDialog = alertDialogBuilder.create()
    alertDialog.show()
  }
  fun deleteEngineers() {
    viewModelScope.launch(Dispatchers.IO) {
      muhendisDao.deleteEngineers()
    }
  }

  fun deleteEngineer(engineerId: Long) {
    viewModelScope.launch(Dispatchers.IO) {
      muhendisDao.deleteMuhendisById(engineerId)
    }
  }
  fun getMuhendisler() {
    viewModelScope.launch(Dispatchers.IO) {
      val projects = muhendisDao.getAllMuhendis()
      _muhendisList.postValue(projects)
    }
  }


}
