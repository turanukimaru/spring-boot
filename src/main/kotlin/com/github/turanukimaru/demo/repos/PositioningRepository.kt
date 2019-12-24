package com.github.turanukimaru.demo.repos

import com.github.turanukimaru.demo.entity.Positioning
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PositioningRepository : JpaRepository<Positioning, String> {
}