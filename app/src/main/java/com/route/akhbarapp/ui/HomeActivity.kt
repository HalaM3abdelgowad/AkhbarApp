package com.route.akhbarapp.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.route.akhbarapp.R

class HomeActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        initView()



    }

    private fun initView() {
        drawerLayout=findViewById(R.id.drawer_layout)
        drawerButton=findViewById(R.id.menu_button)

        //when press in menu button open the drawerlayout
        drawerButton.setOnClickListener{
            drawerLayout.open()
        }
    }
}