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
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secretNumber: ${secretNumber.secret} ")
    }
    fun check(view : View){
        var n=  number.text.toString().toInt()
        println("number : $n")
        Log.d(TAG, "number : $n")
        var message = getString(R.string.great_you_got_it)
        val diff = secretNumber.validate(n)
        if(diff > 0){
            message = getString(R.string.bigger)
        }else if(diff<0){
            message = getString(R.string.smaller)
        }
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.message))
                .setMessage(message)
                .setPositiveButton(resources.getString(R.string.ok),null)
                .show()
    }
}