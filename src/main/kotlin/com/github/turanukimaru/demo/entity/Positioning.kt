package com.github.turanukimaru.demo.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Positioning(
        @Id
        var id: String? = null,
        var x: Int = 0,
        var y: Int = 0,
        @OneToOne
        var battleUnit: BattleUnit? = null
) {
    constructor() : this(null)
}