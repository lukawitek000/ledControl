package com.example.ledcontrolling


import android.util.Log
import java.io.IOException
import java.io.PrintWriter
import java.net.InetAddress
import java.net.Socket
import java.nio.charset.Charset

class TcpClient(private val address: InetAddress, private val port: Int) {
    private lateinit var socket: Socket
    private val UTF8_CHARSET = Charset.forName("UTF-8")

    fun connect() {
        // try {
        socket = Socket(address, port)
        //} catch (e: IOException) {
        //  Log.i("TcpClient", "cannot create socket")
        // throw e
        //}
    }


    fun sendData(data: String?) {
        try {
            val output = socket.getOutputStream()
            val writer = PrintWriter(output, true)
            writer.println(data)
        } catch (e: IOException) {
            Log.i("TcpClient", "send data failure")
            throw e
        }
    }


    fun readDataFromServer() {
        try {
            val input = socket.getInputStream()
            val data = ByteArray(100)
            input.read(data)
            var dataString = String(data, UTF8_CHARSET)
            Log.i("TcpClient", dataString)
        } catch (e: IOException) {
            //e.printStackTrace()
            Log.i("TcpClient", "cannot read data from server")
            throw e
        }
    }

    fun close() {
        try {
            socket.close()
        } catch (e: IOException) {
            Log.i("TcpClient", "cannot close connection")
        }
    }

}
