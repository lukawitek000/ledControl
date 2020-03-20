package com.example.ledcontrolling


import android.os.AsyncTask
import android.util.Log
import java.lang.Exception
import java.net.InetAddress

class ConnectTask() : AsyncTask<String, Void, String>() {
    private val client = TcpClient(InetAddress.getByName("192.168.0.103"), 10000   )

    var delegate: AsyncResponse? = null

    override fun doInBackground(vararg params: String?): String? {
        try {
            client.connect()
            client.sendData(params[0])
            client.readDataFromServer()
            Log.i("someTask", "closing connection")
            client.close()
        }catch(e: Exception){
            return "error"
        }finally {
            //client.close()
        }
        return null
    }
    /*
        override fun onPreExecute() {
            super.onPreExecute()
            // ...
        }
    */
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        delegate?.processFinish(result)
    }

}

interface AsyncResponse{
    fun processFinish(value: String?): Unit
}