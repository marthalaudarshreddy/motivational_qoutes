package com.example.qoutesapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView

class myadapter(var context: Context,var listoi:List<myapidataItem>):RecyclerView.Adapter<myadapter.myviewholder>() {
    class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var qoi:TextView=itemView.findViewById(R.id.qoute)
        var autoi:TextView=itemView.findViewById(R.id.author)
        var copoi:Button=itemView.findViewById(R.id.cooi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
       return myviewholder(LayoutInflater.from(context).inflate(R.layout.itempresoi,parent,false))
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        var current=listoi[position]
        holder.qoi.text=current.text
        holder.autoi.text=current.author
        holder.copoi.setOnClickListener{
            var clipboardManager = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            var clip=ClipData.newPlainText("label",holder.qoi.text)
            clipboardManager.setPrimaryClip(clip)
        }

    }

    override fun getItemCount(): Int {
      return  listoi.size
    }
}