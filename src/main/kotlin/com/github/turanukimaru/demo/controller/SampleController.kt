package com.github.turanukimaru.demo.controller

import com.github.turanukimaru.demo.service.BattleFieldFactory
import jp.blogspot.turanukimaru.fehs.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired

@RestController
class SampleController {
    @Autowired
    var battleFieldFactory: BattleFieldFactory? = null

    @RequestMapping("/")
    fun home(): String = "root"//items()

    @RequestMapping("/items")
    fun items(): SamplesResponse = SamplesResponse(Item.findAll())

    @RequestMapping("/item/{id}")
    fun item(@PathVariable id: Int): SampleResponse = SampleResponse(Item.findById(id))

    @RequestMapping("/model/{id}")
    fun model(@PathVariable id: Int): BattleUnitResponse = BattleUnitResponse(BattleUnit(ArmedHero(StandardBaseHero.get("マルス")!!), id))

}
