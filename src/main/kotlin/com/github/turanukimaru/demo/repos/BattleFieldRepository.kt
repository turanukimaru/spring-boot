package com.github.turanukimaru.demo.repos

import com.github.turanukimaru.demo.entity.JpaPhysicalBoard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BattleFieldRepository : JpaRepository<JpaPhysicalBoard, Int> {
}