package com.example.android.sample.myenglishwords

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        buttonGoToNext.setOnClickListener(this)

        buttonQuitWordTest.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        showQuestion()

        showAnswer()
    }

    private fun showAnswer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showQuestion() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
