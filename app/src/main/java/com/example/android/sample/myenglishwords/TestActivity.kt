package com.example.android.sample.myenglishwords

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

    var intStatus : Int = 0
    val BEFORE_START : Int = 1
    val RUNNING_QUESTION : Int = 2
    val RUNNING_ANSWER : Int = 3
    val TEST_FINISHED : Int = 4

    lateinit var realm : Realm
    lateinit var results : RealmResults<EnglishWordDB>
    lateinit var word_list : ArrayList<EnglishWordDB>

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
    }

    override fun onResume() {
        super.onResume()

        realm = Realm.getDefaultInstance()

        if (boolStatusMemory) {
            results = realm.where(EnglishWordDB::class.java).equalTo(getString(R.string.db_field_flag), false).findAll()
        } else {
            results = realm.where(EnglishWordDB::class.java).findAll()
        }

        word_list = ArrayList(results)
        Collections.shuffle(word_list)
    }

    override fun onPause() {
        super.onPause()

        realm.close()
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
