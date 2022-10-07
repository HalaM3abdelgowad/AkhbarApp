package com.route.akhbarapp.ui

import android.bluetooth.le.AdvertiseSettings
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.akhbarapp.R
import com.route.akhbarapp.ui.categories.CategoriesFragment

class HomeActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerButton: ImageView
    lateinit var categories:View
    lateinit var settings: View

    val categoriesFragment= CategoriesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        initView()
        pushFragment(categoriesFragment)



    }

     fun initView() {
        drawerLayout=findViewById(R.id.drawer_layout)
        drawerButton=findViewById(R.id.menu_button)
        categories=findViewById(R.id.categories)
        settings=findViewById(R.id.settings)
        //when press in menu button open the drawerlayout
        drawerButton.setOnClickListener{
            drawerLayout.open()
        }
        /// click for apper categories fragment
        categories.setOnClickListener {

            pushFragment(categoriesFragment)
        }
        ///click for appear settings fragment
        settings.setOnClickListener {

            pushFragment(SettingsFragment())
        }
    }
/// for push fragment of categories as base fragment
    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()

    }

}