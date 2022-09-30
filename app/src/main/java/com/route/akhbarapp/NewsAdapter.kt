package com.route.akhbarapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.route.akhbarapp.model.ArticlesItem

class NewsAdapter (var items:List<ArticlesItem?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val author:TextView =itemView.findViewById(R.id.author)
        val title:TextView =itemView.findViewById(R.id.title)
        val datetime:TextView =itemView.findViewById(R.id.date_time)
        val image:RoundedImageView =itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items?.get(position)
        holder.author.setText(item?.author)
        holder.title.setText(item?.title)
        holder.datetime.setText(item?.publishedAt)
        //for image
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return  items?.size ?:0
    }
    fun changeData(data:List<ArticlesItem?>?)
    {
        items=data
        notifyDataSetChanged()
    }
}