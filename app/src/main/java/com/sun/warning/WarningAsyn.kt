package com.sun.warning

import android.os.AsyncTask
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class Warning(private val onFetch: OnFetch) : AsyncTask<String, Void, Int>() {
    override fun doInBackground(vararg uri: String?): Int? {
        var responseString: Int? = null
        try {
            val url = URL(uri[0])
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            conn.connect()
            responseString = conn.responseCode
        }catch (e: IOException) {

        }
        return responseString
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        onFetch.getResponse(result!!)
    }

}