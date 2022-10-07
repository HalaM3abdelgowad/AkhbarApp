package com.route.akhbarapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.route.akhbarapp.R
import com.route.akhbarapp.model.Category

class CategoriesFragment :Fragment() {

      //general

    val categoriesList = listOf(
        Category("sports",R.drawable.sports,R.string.sports,R.color.red),
        Category("technology",R.drawable.politics,R.string.politics,R.color.blue),
        Category("health",R.drawable.health,R.string.health,R.color.pink),
        Category("business",R.drawable.bussines,R.string.business,R.color.brown_orange),
        Category("general",R.drawable.environment,R.string.enviroment,R.color.baby_blue),
        Category("science",R.drawable.science,R.string.science,R.color.yellow)
    )

    val adapter=CategoriesAdapter(categoriesList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories,container,false)
    }

    lateinit  var recyclerView : RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     recyclerView = view.findViewById(R.id.recycler_view_categories)
        recyclerView.adapter=adapter
    }
}