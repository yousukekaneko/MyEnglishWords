package com.example.android.sample.myenglishwords

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_word_list.*

class WordListActivity : AppCompatActivity() {

    lateinit var realm: Realm

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
    }

    override fun onResume() {
        super.onResume()

        realm = Realm.getDefaultInstance()

        val results: RealmResults<EnglishWordDB> = realm.where(EnglishWordDB::class.java)
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
}
