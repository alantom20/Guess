package com.home.guess

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.content_material.*
import kotlinx.android.synthetic.main.content_material.counter_guess


class MaterialActivity : AppCompatActivity() {
    private lateinit var viewModel: GuessViewModel
    private val REQUEST_CODE: Int  = 100

    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))
        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java)
        viewModel.counter.observe(this, Observer {data->
            counter_guess.setText(data.toString())
        })
        viewModel.result.observe(this, Observer {result->
            var message = when(result){
                GameResult.BIGGER ->"Bigger"
                GameResult.SMALLER -> "Smaller"
                GameResult.NUMBER_RIGHT ->"Yes! you got it"
            }
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.message))
                .setMessage(message)
                .setPositiveButton(resources.getString(R.string.ok)) { dialog: DialogInterface?, which: Int ->
                    if (result == GameResult.NUMBER_RIGHT) {
                        val intent = Intent(this, RecordActivity::class.java)
                        intent.putExtra("COUNTER", viewModel.count)
                        // startActivity(intent)
                        startActivityForResult(intent,REQUEST_CODE)

                    }
                }
                .show()
        })
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            replay()
        }

        val name = getSharedPreferences("guess", MODE_PRIVATE)
            .getString("REC_NICK",null)
        val count = getSharedPreferences("guess", MODE_PRIVATE)
            .getInt("REC_COUNTER",-1)
        Log.d(TAG, "data : $name / $count")
        //Room read test
        /*AsyncTask.execute {
            var list = GameDatabase.getInstance(this)?.recordDao()?.getAll()
            list?.forEach {
                Log.d(TAG, "onCreate: ${it.nickname} ${it.count}")
            }
        }*/





    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle("Replay Game")
            .setMessage("Are you sure?")
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                viewModel.restart()
                number.setText("")
                counter_guess.setText(viewModel.count.toString())
            }
            .setNeutralButton("Close", null)
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    fun check(view : View){
        var n=  number.text.toString().toInt()
        viewModel.guess(n)
       /* var n=  number.text.toString().toInt()
        println("number : $n")
        Log.d(TAG, "number : $n")
        var message = getString(R.string.great_you_got_it)
        val diff = secretNumber.validate(n)
        if(diff > 0){
            message = getString(R.string.bigger)
        }else if(diff<0){
            message = getString(R.string.smaller)
        }
        counter_guess.setText(secretNumber.count.toString())
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.message))
                .setMessage(message)
                .setPositiveButton(resources.getString(R.string.ok)) { dialog: DialogInterface?, which: Int ->
                    if (diff == 0) {
                        val intent = Intent(this, RecordActivity::class.java)
                        intent.putExtra("COUNTER", secretNumber.count)
                       // startActivity(intent)
                        startActivityForResult(intent,REQUEST_CODE)

                    }
                }
            .show()*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE)
            if(resultCode == RESULT_OK) {
                val name = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: " + name)
                replay()
            }
    }
}