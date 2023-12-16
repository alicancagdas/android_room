package com.alicanadasapplication.app.modules.listproject.data.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alicanadasapplication.app.R
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.database.entities.Projects
import com.alicanadasapplication.app.databinding.EngineerListItemBinding
import com.alicanadasapplication.app.databinding.FragmentListprojectsBinding
import com.alicanadasapplication.app.modules.addengineer.ui.AddEngineerActivity
import com.alicanadasapplication.app.modules.listengineer.data.viewmodel.EngineerListAdapter
import com.alicanadasapplication.app.modules.listengineer.ui.ListEngineerActivity
import com.alicanadasapplication.app.modules.listproject.ui.ListProjectActivity

class ProjectListAdapter(private val context: Context,
                         private val projectList: List<Projects>) : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    class ViewHolder(var view: FragmentListprojectsBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FragmentListprojectsBinding>(inflater,
            R.layout.fragment_listprojects,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.project = projectList[position]
        holder.view.cardCounter.setOnClickListener{
            //buradan update classına yönlendir
            val intent = Intent(context, ListProjectActivity::class.java)
            intent.putExtra("engineerId",projectList[position].managerId)
            Log.d("idlogmesaji", "${projectList[position].managerId}")
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return projectList.size
    }
}
