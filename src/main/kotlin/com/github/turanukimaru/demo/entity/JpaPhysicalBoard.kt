package com.github.turanukimaru.demo.entity

import javax.persistence.*

@Entity
class JpaPhysicalBoard
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        @OneToMany
        var unitList: MutableList<Positioning> = mutableListOf()
        , var ver: Int = 0

) {
    constructor() : this(null)
}