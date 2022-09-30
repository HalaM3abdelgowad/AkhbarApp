package com.route.akhbarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.route.akhbarapp.api.ApiManager
import com.route.akhbarapp.model.NewsResponse
import com.route.akhbarapp.model.SourcesItem
import com.route.akhbarapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout:TabLayout
    lateinit var progressBar:ProgressBar
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getNewsSources()
    }

    val adapter=NewsAdapter(null)
    fun initViews(){
        tabLayout=findViewById(R.id.tab_layout)
        progressBar=findViewById(R.id.progress_bar)
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.adapter=adapter
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
        sources?.forEach {source->
            val tab=tabLayout.newTab()
            tab.setText(source?.name)
            tab.tag=source
            tabLayout.addTab(tab)
        }
        //when click on tab view news
        tabLayout.addOnTabSelectedListener(
            object:TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                 //for get source
                  //  val source=sources?.get(tab?.position?:0)
                    val source=tab?.tag as SourcesItem
                    getNewsBySource(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source=tab?.tag as SourcesItem
                    getNewsBySource(source)
                }

            }
        )
        // for make fist source selected
        tabLayout.getTabAt(0)?.select()

    }

     fun getNewsBySource(source: SourcesItem) {
         progressBar.isVisible=true
         ApiManager.getApis()
             .getNews(Constants.apiKey,source.id?:"")
             .enqueue(object :Callback<NewsResponse>{

                 override fun onResponse(
                     call: Call<NewsResponse>,
                     response: Response<NewsResponse>
                 ) {
                     progressBar.isVisible=false
                     adapter.changeData(response.body()?.articles)
                 }

                 override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                     progressBar.isVisible=false
                 }

             })
    }
}