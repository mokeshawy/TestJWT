package com.example.testjwt

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.io.IOException
import java.util.*

class Utils {

    /* ----- read data from JSON in local ---- */
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDecodedJwtGetBody(jwt: String): String? {
        var result: String? = ""
        val parts = jwt.split("[.]".toRegex()).toTypedArray()
        try {
            val partAsBytes = parts[1].toByteArray(charset("UTF-8"))
            result = String(Base64.getUrlDecoder().decode(partAsBytes), charset("UTF-8"))

        } catch (e: Exception) {
            throw RuntimeException("Couldnt decode jwt", e)
        }
        return result
    }

    fun getRolesFromJWT(json: String) {
        val jsonArray = JSONObject(json).getJSONArray("roles")
        for (index in 0 until jsonArray.length()){
            Log.i("jsonArray",jsonArray[index].toString())
        }
    }
}