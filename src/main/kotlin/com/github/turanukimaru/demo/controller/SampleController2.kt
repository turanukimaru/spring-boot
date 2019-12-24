package com.github.turanukimaru.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("second")
class SampleController2 {
    @GetMapping("/")
    fun home(): String = "second"//items()

    @GetMapping("/items")
    fun items(): SamplesResponse = SamplesResponse(Item.findAll())

    @GetMapping("/item")
    fun item(@PathVariable id: Int): SampleResponse = SampleResponse(Item.findById(id))
}