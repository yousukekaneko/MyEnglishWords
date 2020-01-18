package com.example.android.sample.myenglishwords

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//モデルクラス
open class EnglishWordDB : RealmObject() {
    //フィールドの設定
    @PrimaryKey
    var strQuestion : String = ""
    var strAnswer : String = ""
    var memoryFlag : Boolean = false
}