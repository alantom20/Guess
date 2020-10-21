package com.home.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun check(view : View){
        var n=  number.text.toString().toInt()
        println("number : $n")
        Log.d("MainActivity", "number : $n")
        var message = "Great! you got it"
        val diff = secretNumber.validate(n)
        if(diff > 0){
            message = "big"
        }else if(diff<0){
            message = "small"
        }
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage(message)
                .setPositiveButton("OK",null)
                .show()
    }
}