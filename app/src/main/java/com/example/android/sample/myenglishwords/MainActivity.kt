package com.example.android.sample.myenglishwords

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonEditWords.setOnClickListener{
            val intent = Intent(this@MainActivity, WordListActivity::class.java)
            startActivity(intent)
        }

        buttonCheckTest.setOnClickListener {
            val intent = Intent(this@MainActivity, TestActivity::class.java)
            startActivity(intent)
        }
    }
}
