package com.example.android.sample.myenglishwords

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    lateinit var realm: Realm

    var strQuestion : String = ""
    var strAnswer : String = ""
    var intPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val bundle = intent.extras
        val strStatus = bundle.getString(getString(R.string.intent_key_status))
        textViewStatus.text = strStatus

        if (strStatus == getString(R.string.status_change)) {
            strQuestion = bundle.getString(getString(R.string.intent_key_question))
            strAnswer = bundle.getString(getString(R.string.intent_key_answer))
            editTextQuestion.setText(strQuestion)
            editTextAnswer.setText(strAnswer)

            intPosition = bundle.getInt(getString(R.string.intent_key_position))
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
        val results = realm.where(EnglishWordDB::class.java)
            .findAll().sort(getString(R.string.db_field_question))
        val selectedDB = results[intPosition]

        realm.beginTransaction()

        selectedDB!!.strQuestion = editTextQuestion.text.toString()
        selectedDB!!.strAnswer = editTextAnswer.text.toString()

        realm.commitTransaction()

        editTextQuestion.setText("")
        editTextAnswer.setText("")

        Toast.makeText(this@EditActivity, "修正が完了しました", Toast.LENGTH_SHORT).show()
    }

    private fun addNewWord() {
        realm.beginTransaction()
            val englishWordDB = realm.createObject(EnglishWordDB::class.java)
            englishWordDB.strQuestion = editTextQuestion.text.toString()
            englishWordDB.strAnswer = editTextAnswer.text.toString()
        realm.commitTransaction()

        editTextQuestion.setText("")
        editTextAnswer.setText("")

        Toast.makeText(this@EditActivity, "登録完了", Toast.LENGTH_SHORT).show()
    }
}
