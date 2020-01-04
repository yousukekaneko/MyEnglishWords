package com.example.android.sample.myenglishwords

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_word_list.*

class WordListActivity : AppCompatActivity(), AdapterView.OnItemClickListener,
    AdapterView.OnItemLongClickListener {

    lateinit var realm: Realm

    lateinit var results: RealmResults<EnglishWordDB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        buttonRegister.setOnClickListener {
            val intent = Intent(this@WordListActivity, EditActivity::class.java)
            intent.putExtra(getString(R.string.intent_key_status), getString(R.string.status_add))
            startActivity(intent)
        }
        buttonBack2.setOnClickListener {
            finish()
        }

        listView.onItemClickListener = this

        listView.onItemLongClickListener = this
    }

    override fun onResume() {
        super.onResume()

        realm = Realm.getDefaultInstance()

        results = realm.where(EnglishWordDB::class.java)
            .findAll().sort(getString(R.string.db_field_question))

        val wordList = ArrayList<String>()
        results.forEach {
            wordList.add(it.strQuestion + " : " + it.strAnswer)
        }

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordList)

        listView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()

        realm.close()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedDB = results[position]
        val strSelectedQuestion = selectedDB!!.strQuestion
        val strSelectedAnswer = selectedDB!!.strAnswer

        val intent = Intent(this@WordListActivity, EditActivity::class.java)
        intent.putExtra(getString(R.string.intent_key_question), strSelectedQuestion)
        intent.putExtra(getString(R.string.intent_key_answer), strSelectedAnswer)
        intent.putExtra(getString(R.string.intent_key_position), position)
        intent.putExtra(getString(R.string.intent_key_status), getString(R.string.status_change))
        startActivity(intent)
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {

        val selectedDB = results[position]

        realm.beginTransaction()
        selectedDB!!.deleteFromRealm()
        realm.commitTransaction()
    }

}
