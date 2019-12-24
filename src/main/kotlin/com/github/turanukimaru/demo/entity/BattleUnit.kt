package com.github.turanukimaru.demo.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class BattleUnit(
        @Id
        var id: String? = null
        , var hp: Int = 0
) {
    constructor() : this(null)
}