package de.leonwehrwein.weatho

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private var tv: TextView? = null
    private var reloadBtn: Button? = null
    private var settingsBtn: Button? = null
    private var apiKeyManager = APIKeyManager()

    var cityName = ""
    var temp = 0.0
    var description = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById<TextView>(R.id.textView)
        reloadBtn = findViewById<Button>(R.id.reloadBtn)
        settingsBtn = findViewById<Button>(R.id.settingsBtn)

        reloadBtn?.setOnClickListener { updateCurrentWeather() }
        settingsBtn?.setOnClickListener {
            startActivity(Intent(this, Settings::class.java))
            updateCurrentWeather()
        }

        updateCurrentWeather()
    }


    fun updateCurrentWeather(){
        var APIKey = apiKeyManager.loadAPIKey(this.applicationContext)
        if(APIKey!= null){
            val queue = Volley.newRequestQueue(this)
            val url = "https://api.openweathermap.org/data/2.5/weather?lat=51.683334&lon=7.816667&appid=$APIKey&lang=de"
            val request = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    cityName = response.getString("name")
                    temp = (response.getJSONObject("main").getDouble("temp") - 273.15)
                    description = response.getJSONArray("weather").getJSONObject(0).getString("description")
                    println("Updated Current Weather Data")
                    displayCurrentWeather()
                },
                {error -> println(error.toString()) })

            queue.add(request)
        }

        this.applicationContext
    }

    @SuppressLint("SetTextI18n")
    fun displayCurrentWeather(){
        tv?.text = "$cityName $temp $description"
    }



}