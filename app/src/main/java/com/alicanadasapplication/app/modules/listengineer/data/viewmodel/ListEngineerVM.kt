package com.alicanadasapplication.app.modules.listengineer.`data`.viewmodel

import android.app.AlertDialog
import android.content.ComponentCallbacks
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.database.entities.Projects
import com.alicanadasapplication.app.modules.addengineer.data.model.AddEngineerModel
import com.alicanadasapplication.app.modules.listengineer.`data`.model.ListEngineerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

class ListEngineerVM(private val muhendisDao: MuhendisDao, private val initialModel: ListEngineerModel) : ViewModel() {
  val listEngineerModel: MutableLiveData<ListEngineerModel> = MutableLiveData(ListEngineerModel())
  private val _muhendisList = MutableLiveData<List<Muhendisler>>()
  private lateinit var listOfEngineers : ArrayList<Muhendisler>
  var navArguments: Bundle? = null





  fun getEngineers(callbacks:(List<Muhendisler>) -> Unit) {
    listOfEngineers = ArrayList()
    viewModelScope.launch(Dispatchers.IO) {
      val muhendisler = muhendisDao.getAllMuhendis()
      withContext(Dispatchers.Main) {
       listOfEngineers.addAll(muhendisler)
        callbacks(listOfEngineers)
      }

    }
  }

  fun getCountThreeEngineers(callbacks:(List<Muhendisler>) -> Unit) {
    listOfEngineers = ArrayList()
    viewModelScope.launch(Dispatchers.IO) {
      val muhendisler = muhendisDao.getMuhendisWithMoreThanThreeProjects()
      println(muhendisler)
      withContext(Dispatchers.Main) {
        listOfEngineers.addAll(muhendisler)
        callbacks(listOfEngineers)
      }
    }
  }

}