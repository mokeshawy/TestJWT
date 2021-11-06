package com.example.testjwt

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val utils = Utils()
        val jsonFile = utils.getJsonDataFromAsset(applicationContext, "bezkoder.json")
        Log.d("json", jsonFile.toString() )


        val gson = Gson()
        val listPersonType = object : TypeToken<List<Person>>() {}.type

        val persons : List<Person> = gson.fromJson(jsonFile,listPersonType)
        persons.forEachIndexed { index , person ->
            Log.d("data", "> Item $index:\n$person")
        }

        Log.d("readMsg", readToken()!!)

        utils.getRolesFromJWT(" JWT KEY ")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readToken(): String? {
       return Utils().getDecodedJwtGetBody("JWT KEY")
    }
}