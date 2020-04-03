package com.example.ledcontrolling


import android.os.AsyncTask
import android.util.Log
import java.lang.Exception
import java.net.InetAddress

class ConnectTask() : AsyncTask<String, Void, String>() {
    companion object {
        var host= "192.168.0.112"
        var port = 9999
        //private val client = TcpClient(InetAddress.getByName(host), port)
    }

    var delegate: AsyncResponse? = null

    override fun doInBackground(vararg params: String?): String? {
        try {
            val client = TcpClient(InetAddress.getByName(host), port)
            //Log.i("someTask", "createobject")
            client.connect()
            //Log.i("someTask", "connection")
            client.sendData(params[0])
            //Log.i("someTask", "sendData")
            client.readDataFromServer()
            //Log.i("someTask", "closing connection")
            client.close()
        }catch(e: Exception){
            return "error"
        }finally {
            //client.close()
        }
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        delegate?.processFinish(result)
    }

}

interface AsyncResponse{
    fun processFinish(value: String?): Unit
}