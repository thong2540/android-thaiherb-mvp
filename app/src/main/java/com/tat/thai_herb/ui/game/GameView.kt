package com.tat.thai_herb.ui.game

import com.tat.thai_herb.model.respone.DataGame
import com.tat.thai_herb.model.respone.SearchList

class GameView {
    interface View {
        fun respone(list: List<DataGame>)
    }
}