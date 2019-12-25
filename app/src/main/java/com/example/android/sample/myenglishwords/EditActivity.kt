package com.example.android.sample.myenglishwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        buttonAddNewWords.setOnClickListener {  }
        buttonBack2.setOnClickListener {  }
    }
}
