package com.home.guess.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class Record(@NotNull
             @ColumnInfo(name = "nick")
             var nickname : String,
             @NotNull
             var count : Int) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}

@Entity
class Word{
    @PrimaryKey
    var word :String = ""
    var means:String = ""
    var star :Int = 0
}