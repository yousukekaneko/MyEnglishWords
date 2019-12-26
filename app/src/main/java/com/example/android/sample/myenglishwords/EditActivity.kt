package com.example.android.sample.myenglishwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        buttonRegister.setOnClickListener {

            addNewWord()

            changeWord()
        }
        buttonBack2.setOnClickListener {  }
    }

    private fun changeWord() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addNewWord() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
