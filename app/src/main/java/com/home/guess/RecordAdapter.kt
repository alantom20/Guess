package com.home.guess

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.guess.data.Record
import kotlinx.android.synthetic.main.row_record.view.*

class RecordAdapter(var records:List<Record>) : RecyclerView.Adapter<RecordHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordHolder {
        return RecordHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_record,parent,false))
    }

    override fun onBindViewHolder(holder: RecordHolder, position: Int) {
        holder.nickText.text = records.get(position).nickname
        holder.countText.text = records.get(position).count.toString()

    }

    override fun getItemCount(): Int {
        return records.size
    }

}

class  RecordHolder(view: View): RecyclerView.ViewHolder(view){
    var nickText = view.record_nickname
    var countText = view.record_count

}