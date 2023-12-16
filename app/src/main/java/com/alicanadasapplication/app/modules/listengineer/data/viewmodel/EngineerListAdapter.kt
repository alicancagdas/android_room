package com.alicanadasapplication.app.modules.listengineer.data.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.databinding.EngineerListItemBinding
import com.alicanadasapplication.app.modules.addengineer.ui.AddEngineerActivity
import com.alicanadasapplication.app.modules.editengineer.data.viewmodel.EditEngineerActivity
import com.alicanadasapplication.app.modules.listengineer.data.model.ListEngineerModel
import kotlin.math.log

// EngineerListAdapter.kt
class EngineerListAdapter(private val context: Context,
    private val engineerList: List<Muhendisler>) : RecyclerView.Adapter<EngineerListAdapter.ViewHolder>() {

    class ViewHolder(var view: EngineerListItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<EngineerListItemBinding>(inflater,R.layout.engineer_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.engineer = engineerList[position]
        holder.view.cardCounter.setOnClickListener{
            //buradan update classına yönlendir
            val intent = Intent(context, AddEngineerActivity::class.java)
            intent.putExtra("engineerId",engineerList[position].engineerId)
            Log.d("idlogmesaji", "${engineerList[position].engineerId}")
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return engineerList.size
    }
}
