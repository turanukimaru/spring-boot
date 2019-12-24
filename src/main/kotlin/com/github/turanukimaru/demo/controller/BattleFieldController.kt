package com.github.turanukimaru.demo.controller

import com.github.turanukimaru.demo.service.BattleFieldFactory
import com.github.turanukimaru.demo.usecase.BattleField
import com.github.turanukimaru.demo.usecase.BattleSim
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired

@RestController
class BattleFieldController {
    @Autowired
    var battleFieldFactory: BattleFieldFactory? = null

    @RequestMapping("/fight/{source}/{target}")
    fun fight(@PathVariable source: String, @PathVariable target: String): FightResultPresenter = BattleSim().fight(source,target)

    @RequestMapping("/field/new")
    fun newField(): String = BattleField(battleFieldFactory!!).newField()

    @RequestMapping("/field/{id}")
    fun findField(@PathVariable id: Int): String = BattleField(battleFieldFactory!!).find(id)

    @RequestMapping("/field/{id}/put/{name}/{x}/{y}")
    fun putField(@PathVariable id: Int, @PathVariable name: String, @PathVariable x: Int, @PathVariable y: Int): String = BattleField(battleFieldFactory!!).put(id, name, x, y)

    @RequestMapping("/field/{id}/move/{name}/{x}/{y}")
    fun moveField(@PathVariable id: Int, @PathVariable name: String, @PathVariable x: Int, @PathVariable y: Int): String = BattleField(battleFieldFactory!!).move(id, name, x, y)
}

