package com.github.turanukimaru.demo.service

import com.github.turanukimaru.demo.entity.JpaPhysicalBoard
import com.github.turanukimaru.demo.repos.BattleFieldRepository
import com.github.turanukimaru.demo.repos.BattleUnitRepository
import com.github.turanukimaru.demo.repos.PositioningRepository
import jp.blogspot.turanukimaru.fehs.Ground
import jp.blogspot.turanukimaru.fehs.MyPiece
import jp.blogspot.turanukimaru.fieldrepos.PersistPhysicalBoard
import jp.blogspot.turanukimaru.playboard.Board
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Service
class BattleFieldFactory {
    @Autowired
    var battleFieldRepository: BattleFieldRepository? = null
    @Autowired
    var battleUnitRepository: BattleUnitRepository? = null
    @Autowired
    var positioningRepository: PositioningRepository? = null
    @Autowired
    var em: EntityManager? = null

    fun findAll(): List<JpaPhysicalBoard> = battleFieldRepository!!.findAll().toList()

    @Transactional
    fun findBoardByRepos(id: Int) = Board(6, 8, id,
            PersistPhysicalBoard(6, 8, battleFieldRepository!!.getOne(id), this))
    // getOne と findByIdOrNull は optional であるかだけなのだろうか…？
//            PersistPhysicalBoard(6,8,battleFieldRepository!!.findByIdOrNull(id)!!,this))

    @Transactional
    fun findBoard(id: Int) = Board(6, 8, id, PersistPhysicalBoard(6, 8, em!!.find(JpaPhysicalBoard::class.java, id), this))

    @Transactional
    fun createBoard(): Board<MyPiece, Ground> {
        val result = create(6, 8)
        return Board(6, 8, result.physicalBoard.id
                , result)  //マネージドボードをリポジトリから取得

    }

    fun create(x: Int = 6, y: Int = 8): PersistPhysicalBoard {
        val newField = JpaPhysicalBoard()
        battleFieldRepository!!.save(newField)
        return PersistPhysicalBoard(x, y, newField, this)
    }

    fun save(jpaPhysicalBoard: JpaPhysicalBoard) = battleFieldRepository!!.save(jpaPhysicalBoard)

    fun delete(id: Int) = battleFieldRepository!!.deleteById(id)

}