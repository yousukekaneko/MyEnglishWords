package com.example.android.sample.myenglishwords

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*
import kotlin.collections.ArrayList

class TestActivity : AppCompatActivity(), View.OnClickListener {

    var boolStatusMemory : Boolean = false
    var boolmemorize : Boolean = false

    var intStatus : Int = 0
    val BEFORE_START : Int = 1
    val RUNNING_QUESTION : Int = 2
    val RUNNING_ANSWER : Int = 3
    val TEST_FINISHED : Int = 4

    lateinit var realm : Realm
    lateinit var results : RealmResults<EnglishWordDB>
    lateinit var word_list : ArrayList<EnglishWordDB>

    var intLength : Int = 0
    var intCounter : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val bundle = intent.extras
        boolStatusMemory = bundle.getBoolean(getString(R.string.intent_key_memory_flag))

        intStatus = BEFORE_START
        flashCardQuestion.visibility = View.INVISIBLE
        flashCardAnswer.visibility = View.INVISIBLE

        buttonGoToNext.setBackgroundResource(R.drawable.image_button_test_start)
        buttonQuitWordTest.setBackgroundResource(R.drawable.image_button_end_test)

        buttonGoToNext.setOnClickListener(this)
        buttonQuitWordTest.setOnClickListener(this)

        checkBox.setOnClickListener {
            boolmemorize = checkBox.isChecked
        }
    }

    override fun onResume() {
        super.onResume()

        realm = Realm.getDefaultInstance()

        if (boolStatusMemory) {
            results = realm.where(EnglishWordDB::class.java).equalTo(getString(R.string.db_field_flag), false).findAll()
        } else {
            results = realm.where(EnglishWordDB::class.java).findAll()
        }

        intLength = results.size
        textViewRemaining.text = intLength.toString()

        word_list = ArrayList(results)
        Collections.shuffle(word_list)
    }

    override fun onPause() {
        super.onPause()

        realm.close()
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.buttonGoToNext ->
                when(intStatus) {
                    BEFORE_START -> {
                        intStatus = RUNNING_QUESTION
                        showQuestion()
                    }
                    RUNNING_QUESTION -> {
                        intStatus = RUNNING_ANSWER
                        showAnswer()
                    }

                    RUNNING_ANSWER -> {
                        intStatus = RUNNING_QUESTION
                        showQuestion()
                    }

                }

            R.id.buttonQuitWordTest -> {

                val dialig = AlertDialog.Builder(this@TestActivity).apply {
                    setTitle("finish")
                    setMessage(getString(R.string.end_message))
                    setPositiveButton("Yes") { _ , _ ->
                        if (intLength == intCounter) {
                            val selectedDB = realm.where(EnglishWordDB::class.java).equalTo(getString(R.string.db_field_question),
                                word_list[intCounter - 1].strQuestion).findFirst()!!
                            realm.beginTransaction()
                            selectedDB.memoryFlag = boolmemorize
                            realm.commitTransaction()
                        }
                        finish()
                    }
                    setNegativeButton("No") { _ , _ ->

                    }
                    show()
                }
            }
        }




    }

    private fun showAnswer() {

        flashCardAnswer.visibility = View.VISIBLE
        textFlashCardAnswer.text = word_list[intCounter - 1].strAnswer

        buttonGoToNext.setBackgroundResource(R.drawable.image_button_go_next_question)

        if (intLength == intCounter) {
            intStatus = TEST_FINISHED

            textFinishMessage.text = "finish"
        }

        buttonGoToNext.isEnabled = false
        buttonGoToNext.visibility = View.INVISIBLE

        buttonQuitWordTest.setBackgroundResource(R.drawable.image_button_back)

    }


    private fun showQuestion() {

        if (intCounter > 0) {
            val selectedDB = realm.where(EnglishWordDB::class.java).equalTo(getString(R.string.db_field_question),
                word_list[intCounter - 1].strQuestion).findFirst()!!
            realm.beginTransaction()
            selectedDB.memoryFlag = boolmemorize
            realm.commitTransaction()
        }

        intCounter ++
        textViewRemaining.text = (intLength - intCounter).toString()

        flashCardAnswer.visibility = View.INVISIBLE
        textFlashCardAnswer.text = ""
        flashCardQuestion.visibility = View.VISIBLE
        textFlashCardQuestion.text = word_list[intCounter -1].strQuestion

        buttonGoToNext.setBackgroundResource(R.drawable.image_button_go_next_question)

        checkBox.isChecked = word_list[intCounter -1].memoryFlag
        boolmemorize = checkBox.isChecked
    }
}
