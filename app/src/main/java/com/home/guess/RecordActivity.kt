package com.home.guess

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.home.guess.data.GameDatabase
import com.home.guess.data.Record
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class RecordActivity : AppCompatActivity() , CoroutineScope {
    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        job = Job()
        val count = intent.getIntExtra("COUNTER",-1)
        counter.setText(count.toString())
        //OnClickListener
        save.setOnClickListener { view ->
            val name = nickname.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER",count)
                .putString("REC_NICK",name)
                .apply()
            launch {
                GameDatabase.getInstance(this@RecordActivity)?.recordDao()?.
                insert(Record(name,count))
            }
            val intent = Intent()
            intent.putExtra("NICK",name)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}