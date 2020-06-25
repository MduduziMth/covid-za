package com.example.android.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import com.example.android.myapplication.model.Covid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.temporal.ValueRange

class MainActivity : AppCompatActivity() {

    var activeNow: Int = 0
    var recovered: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.covid19api.com/")
            .build()

        val covidApi = retrofit.create(CovidApi::class.java)

        val myCallActive: Call<List<Covid>> = covidApi.getCases()

        val myCallRecover: Call<List<Covid>> = covidApi.getRecoveries()

        val myCallDeath: Call<List<Covid>> = covidApi.getDeaths()


        myCallActive.enqueue(object : Callback<List<Covid>>{
            override fun onFailure(call: Call<List<Covid>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(call: Call<List<Covid>>, response: Response<List<Covid>>) {
                val Cases:List<Covid> = response.body()!!

                val active = findViewById<TextView>(R.id.active_text)
                active.text = Cases.elementAt(Cases.lastIndex).cases.toString()

            }

        })

        myCallRecover.enqueue(object : Callback<List<Covid>>{
            override fun onFailure(call: Call<List<Covid>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(call: Call<List<Covid>>, response: Response<List<Covid>>) {
                val Cases:List<Covid> = response.body()!!

                val recover = findViewById<TextView>(R.id.recovered_text)
                recover.text = Cases.elementAt(Cases.lastIndex).cases.toString()



            }

        })

        myCallDeath.enqueue(object : Callback<List<Covid>>{
            override fun onFailure(call: Call<List<Covid>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(call: Call<List<Covid>>, response: Response<List<Covid>>) {
                val Cases:List<Covid> = response.body()!!



                    val deaths = findViewById<TextView>(R.id.deaths_text)
                    deaths.text = Cases.elementAt(Cases.lastIndex).cases.toString()




            }

        })

/*        val act = findViewById<TextView>(R.id.active_text)
        var active:String = act.text.toString()

        val rec = findViewById<TextView>(R.id.recovered_text)
        var reco:String = rec.text.toString()


        val death = findViewById<TextView>(R.id.deaths_text)
        var deathss:String = death.text.toString()


        var total:Int = (active.toInt()-(reco.toInt()+deathss.toInt()))

        val sick = findViewById<TextView>(R.id.sick_text)
        sick.text = total.toString()*/


        Handler().postDelayed({
            val act = findViewById<TextView>(R.id.active_text)
            var active:String = act.text.toString()

            val rec = findViewById<TextView>(R.id.recovered_text)
            var reco:String = rec.text.toString()


            val death = findViewById<TextView>(R.id.deaths_text)
            var deathss:String = death.text.toString()


            var total:Int = (active.toInt()-(reco.toInt()+deathss.toInt()))

            val sick = findViewById<TextView>(R.id.sick_text)
            sick.text = total.toString()



        }, 1500)




    }

    fun getIntegerActive(case: String){

        activeNow = case.toInt()
    }

    fun getIntegerRecover(case: Int){
        recovered = case
    }
}