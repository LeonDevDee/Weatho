package de.leonwehrwein.weatho

import android.content.Context
import android.widget.EditText

class APIKeyManager {

    fun loadAPIKey(context: Context) : String?{
        val filename= "ApiKey"
        try {
            var APIKey = context.openFileInput(filename).bufferedReader().readLine().toString()
            println("APIKey loaded: $APIKey")
            return APIKey
        }catch (e:java.lang.Exception){
            println(e.stackTrace)
        }
        return null
    }

    fun saveAPIKey(context: Context, apiKey: String){

        val filename = "ApiKey"
        val fileContents: String = apiKey
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }

        println("Saved $fileContents as API Key")
    }
}