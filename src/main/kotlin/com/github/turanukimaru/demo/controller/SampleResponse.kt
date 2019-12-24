package com.github.turanukimaru.demo.controller

import jp.blogspot.turanukimaru.fehs.BattleUnit
import jp.blogspot.turanukimaru.fehs.FightResult
import jp.blogspot.turanukimaru.fehs.Locale

data class BattleUnitResponse(val item: BattleUnit)
data class SampleResponse(val item: Item?)
data class SamplesResponse(val item: List<Item>)
data class FightResultPresenter(val attacker: String, val target: String, val resultTexts: List<String>) {
    companion object {
        fun ofFightResult(r: FightResult): FightResultPresenter {
            val texts = arrayListOf<String>()
            return FightResultPresenter(r.attacker.statusText(Locale.JAPANESE), r.target.statusText(Locale.JAPANESE), texts)
        }
    }
}

data class Item(val id: Int, val name: String) {
    companion object {
        val ITEMS = listOf(
                Item(1, "アイク"),
                Item(2, "バアトル")
        )

        fun findAll(): List<Item> = ITEMS
        fun findById(id: Int): Item? = ITEMS.find { it.id == id }
    }
}
