package com.github.turanukimaru.demo.repos

import com.github.turanukimaru.demo.entity.BattleUnit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BattleUnitRepository : JpaRepository<BattleUnit, String> {
}