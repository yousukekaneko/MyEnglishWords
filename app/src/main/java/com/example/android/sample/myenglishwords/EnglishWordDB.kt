package com.example.android.sample.myenglishwords

import io.realm.RealmObject

//モデルクラス
open class EnglishWordDB : RealmObject() {
    //フィールドの設定
    var strQuestion : String = ""
    var strAnswer : String = ""
}