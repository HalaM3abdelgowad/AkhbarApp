package com.route.akhbarapp.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.route.akhbarapp.R
import com.route.akhbarapp.model.Category

class CategoriesAdapter(val categories:List<Category>)
    :RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {



    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val title:TextView=itemView.findViewById(R.id.title)
        val image:ImageView=itemView.findViewById(R.id.image)
        val materialCard:MaterialCardView=itemView.findViewById(R.id.material_card)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(
                if (viewType==LEFT_SIDED_VIEW_TYPE)
                    R.layout.left_sided_category
                else R.layout.right_sided_category
            ,parent,false)
        return ViewHolder(view)
    }
    val LEFT_SIDED_VIEW_TYPE=10
    val RIGHT_SIDED_VIEW_TYPE=20

    override fun getItemViewType(position: Int): Int {
        if(position%2==0)
            return LEFT_SIDED_VIEW_TYPE
        else
            return RIGHT_SIDED_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=categories[position]
        holder.title.setText(item.titleId)
        holder.image.setImageResource(item.imageResourceId)
        holder.materialCard.setCardBackgroundColor(ContextCompat
            .getColor(holder.itemView.context,item.backgroundColorId))
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}