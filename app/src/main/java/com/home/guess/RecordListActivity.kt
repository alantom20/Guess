package com.home.guess

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.home.guess.data.GameDatabase
import com.home.guess.data.RecordDao
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordListActivity : AppCompatActivity() ,CoroutineScope {
    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        job = Job()
        //get record
        launch {
            var records = GameDatabase.getInstance(this@RecordListActivity)?.recordDao()?.getAll()
            records?.let {
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(this@RecordListActivity)
                    recycler.adapter = RecordAdapter(it)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}