package com.example.android.sample.myenglishwords

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//モデルクラス
open class EnglishWordDB : RealmObject() {
    //フィールドの設定
    @PrimaryKey
    open var strQuestion : String = ""
    open var strAnswer : String = ""
    open var memoryFrag : Boolean = false
}