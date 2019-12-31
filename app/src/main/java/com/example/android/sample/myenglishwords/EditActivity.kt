package com.example.android.sample.myenglishwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val bundle = intent.extras
        val strStatus = bundle.getString(getString(R.string.intent_key_status))
        textViewStatus.text = strStatus

        if (strStatus == getString(R.string.status_change)) {

        }

        buttonRegister.setOnClickListener {

            if (strStatus == getString(R.string.status_add)) {
                addNewWord()
            } else {
                changeWord()
            }
        }

        buttonBack2.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        realm = Realm.getDefaultInstance()
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }

    private fun changeWord() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun addNewWord() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
