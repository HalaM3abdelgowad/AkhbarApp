package com.route.akhbarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.route.akhbarapp.api.ApiManager
import com.route.akhbarapp.model.SourcesItem
import com.route.akhbarapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout:TabLayout
    lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout=findViewById(R.id.tab_layout)
        progressBar=findViewById(R.id.progress_bar)
        getNewsSources()
    }

    private fun getNewsSources() {
        ApiManager.getApis().getSource(Constants.apiKey)
            .enqueue(object :Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible=false
                    addSourcesToTabLayout(response.body()?.sources)
                   // Log.e("data",response.body().toString())
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("error",t.localizedMessage)
                }

            })
    }

    private fun addSourcesToTabLayout(sources:List<SourcesItem?>?) {
        sources?.forEach {
            val tab=tabLayout.newTab()
            tab.setText(it?.name)
            tabLayout.addTab(tab)
        }

    }
}