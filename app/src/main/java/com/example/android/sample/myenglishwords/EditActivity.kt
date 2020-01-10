package com.example.android.sample.myenglishwords

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

            editTextQuestion.isEnabled = false
        } else  {
            editTextQuestion.isEnabled = true
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

//        selectedDB!!.strQuestion = editTextQuestion.text.toString()
        selectedDB!!.strAnswer = editTextAnswer.text.toString()
        selectedDB!!.memoryFrag = false

        realm.commitTransaction()

        editTextQuestion.setText("")
        editTextAnswer.setText("")

        Toast.makeText(this@EditActivity, "修正が完了しました", Toast.LENGTH_SHORT).show()
    }

    private fun addNewWord() {

        val dialog = AlertDialog.Builder(this@EditActivity).apply {
            setTitle("Register")
            setMessage("Are you sure you want to register?")
            setPositiveButton("Yes") { dialog, which ->

                realm.beginTransaction()
                val englishWordDB = realm.createObject(EnglishWordDB::class.java, editTextQuestion.text.toString())
                englishWordDB.strAnswer = editTextAnswer.text.toString()
                englishWordDB.memoryFrag = false
                realm.commitTransaction()

                editTextQuestion.setText("")
                editTextAnswer.setText("")

                Toast.makeText(this@EditActivity, "Completion of registration!", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("No") { dialog, which -> }
            show()
        }

        realm.beginTransaction()
            val englishWordDB = realm.createObject(EnglishWordDB::class.java, editTextQuestion.text.toString())
            englishWordDB.strAnswer = editTextAnswer.text.toString()
            englishWordDB.memoryFrag = false
        realm.commitTransaction()

        editTextQuestion.setText("")
        editTextAnswer.setText("")

        Toast.makeText(this@EditActivity, "登録完了", Toast.LENGTH_SHORT).show()
    }
}
