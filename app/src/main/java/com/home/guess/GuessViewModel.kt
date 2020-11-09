package com.home.guess

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GuessViewModel : ViewModel(){
    var secret = Random().nextInt(10)+1
    var count = 0
    val counter = MutableLiveData<Int>()
    val result = MutableLiveData<GameResult>()
    init {
        counter.value = count
        restart()
    }

    fun guess(num : Int){
        count++
        counter.value = count
        val gameResult = when(num - secret){
            0 ->GameResult.NUMBER_RIGHT
            in 1..Int.MAX_VALUE -> GameResult.SMALLER
            else ->  GameResult.BIGGER
        }
        result.value =  gameResult
    }
    fun restart(){
        secret = Random().nextInt(10)+1
        count = 0
        println(secret)
    }

}
enum class GameResult{
    BIGGER,SMALLER,NUMBER_RIGHT
}