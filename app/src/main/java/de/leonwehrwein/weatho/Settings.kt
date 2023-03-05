package de.leonwehrwein.weatho

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.io.File

class Settings : AppCompatActivity() {
    var APIKey: String? =null
    var apiKeyManager =  APIKeyManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val saveBtn: Button =  findViewById<Button>(R.id.saveSettingBtn)
        val editText = findViewById<EditText>(R.id.editTextApiKey)

        APIKey = apiKeyManager.loadAPIKey(applicationContext)
        if(APIKey !=  null){
            editText.setText(APIKey)
        }


        saveBtn.setOnClickListener {
            apiKeyManager.saveAPIKey(applicationContext,editText.text.toString())
            finish()
        }

    }



}
