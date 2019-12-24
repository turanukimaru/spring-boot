package com.github.turanukimaru.demo.usecase

import com.github.turanukimaru.demo.service.BattleFieldFactory
import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.playboard.*

class BattleField(val battleFieldFactory: BattleFieldFactory) {
    val battleGround = arrayOf(
            arrayOf(Ground.P, Ground.P, Ground.W, Ground.R, Ground.M, Ground.M),
            arrayOf(Ground.R, Ground.P, Ground.R, Ground.R, Ground.M, Ground.M),
            arrayOf(Ground.P, Ground.P, Ground.M, Ground.M, Ground.M, Ground.M),
            arrayOf(Ground.P, Ground.P, Ground.M, Ground.M, Ground.M, Ground.M),
            arrayOf(Ground.M, Ground.P, Ground.P, Ground.P, Ground.M, Ground.M),
            arrayOf(Ground.M, Ground.P, Ground.P, Ground.P, Ground.P, Ground.P),
            arrayOf(Ground.M, Ground.M, Ground.M, Ground.P, Ground.P, Ground.P),
            arrayOf(Ground.M, Ground.M, Ground.M, Ground.M, Ground.P, Ground.P)

    )
    var user = Player()
    var enemy = Player()
    fun newField(): String {
        //依存先はbattlefield.まだGameではなくていいか？Gameにすると手番の概念が存在するが…
        val board: Board<MyPiece, Ground> = battleFieldFactory.createBoard()
        board.physics.copyGroundSwitchXY(battleGround)
        val piece1 = MyPiece(BattleUnit(ArmedHeroRepository.getById("マルス")!!, 40), board, user)
        board.physics.put(piece1, 3, 3)
        return board.id.toString()
    }

    fun put(id: Int, name: String, x: Int, y: Int): String {
        //依存先はbattlefield.まだGameではなくていいか？Gameにすると手番の概念が存在するが…
        val board: Board<MyPiece, Ground> = battleFieldFactory.findBoard(id)
        board.physics.copyGroundSwitchXY(battleGround)
        val piece1 = MyPiece(BattleUnit(ArmedHeroRepository.getById(name)!!, 40), board, user)
        board.physics.put(piece1, x, y)
        return board.present()
    }

    fun find(id: Int): String {
        //依存先はbattlefield.まだGameではなくていいか？Gameにすると手番の概念が存在するが…
        val board: Board<MyPiece, Ground> = battleFieldFactory.findBoard(id)
        board.physics.copyGroundSwitchXY(battleGround)
        return board.present()
    }

    fun move(id: Int, name: String, x: Int, y: Int): String {
        //依存先はbattlefield.まだGameではなくていいか？Gameにすると手番の概念が存在するが…
        val board: Board<MyPiece, Ground> = battleFieldFactory.findBoard(id)
        board.physics.copyGroundSwitchXY(battleGround)
        val piece1 = MyPiece(BattleUnit(ArmedHeroRepository.getById(name)!!, 40), board, user)
        board.physics.move(piece1, Position(x, y))
        return board.present()
    }

    private fun Board<MyPiece, Ground>.present(): String {
        val sb = StringBuilder()
        val nameSb = StringBuilder()
        //SDキャラの代わりに画面に表示させる文字
        val names = listOf("A", "B", "C", "D", "E", "F")
        var nextName = 0
        // android 画面は上下反転している！
        physics.verticalIndexes.reversed().forEach { y ->
            physics.horizontalIndexes.forEach { x ->
                val p = Position(x, y)
                val g = physics.groundAt(p)
                val u = physics.pieceAt(p)
                if (u != null) {
                    val initial = names[nextName++]
                    sb.append(initial).append("|")

                    nameSb.append("$initial : ${u.unit.containUnit.armedHero.localeName(Locale.JAPANESE)}").append("<br />\r\n")
                } else {
                    sb.append(g?.name ?: "").append("|")
                }
            }
            sb.append("<br />\r\n")
        }
        return sb.toString() + nameSb.toString()
    }
}
