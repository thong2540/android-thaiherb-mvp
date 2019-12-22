package com.tat.thai_herb.utilty.game.quiz.utilty

object RandomList {

    fun renDomItem(list: List<Int>, size: Int) : List<Int> {
        var mList = arrayListOf<Int>()
        var countId = true
        while (countId) {
            val id = list.random()
            if (mList.filter { it == id }.size < 2) {
                mList.add(id)
                if (mList.size == size) {
                    countId = false
                }
            }
        }
        println("list = $mList")
        return mList
    }
}