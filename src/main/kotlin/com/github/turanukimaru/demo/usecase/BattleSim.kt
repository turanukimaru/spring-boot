package com.github.turanukimaru.demo.usecase

import com.github.turanukimaru.demo.controller.FightResultPresenter
import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.BattleUnit
import jp.blogspot.turanukimaru.fehs.FightResult
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import jp.blogspot.turanukimaru.playboard.Position

class BattleSim {

    fun fight(source : String, target : String): FightResultPresenter {
        val unitA = ArmedHero(StandardBaseHero.get(source)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(target)!!)
        val target = BattleUnit(unitB, unitB.maxHp)
        val fightResult = attacker.fightAndAfterEffect(target)
        return FightResultPresenter.ofFightResult(FightResult(attacker, Position(0, 0), target, Position(0, 0), fightResult))
    }
}