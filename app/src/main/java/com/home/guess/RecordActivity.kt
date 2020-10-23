package com.home.guess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*


class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER",-1)
        counter.setText(count.toString())
        //OnClickListener
        save.setOnClickListener { view ->
            val name = nickname.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER",count)
                .putString("REC_NICK",name)
                .commit()
            val intent = Intent()
            intent.putExtra("NICK",name)
            setResult(RESULT_OK,intent)
            finish()

        }



    }
}