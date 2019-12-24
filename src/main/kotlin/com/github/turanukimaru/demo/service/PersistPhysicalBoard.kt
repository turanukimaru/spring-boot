package jp.blogspot.turanukimaru.fieldrepos

import com.github.turanukimaru.demo.entity.BattleUnit
import com.github.turanukimaru.demo.entity.JpaPhysicalBoard
import com.github.turanukimaru.demo.entity.Positioning
import com.github.turanukimaru.demo.service.BattleFieldFactory
import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.Ground
import jp.blogspot.turanukimaru.fehs.MyPiece
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import jp.blogspot.turanukimaru.playboard.*

class PersistPhysicalBoard(horizontalLines: Int, verticalLines: Int, var physicalBoard: JpaPhysicalBoard, val repos: BattleFieldFactory) : PhysicalBoard<MyPiece, Ground>(horizontalLines, verticalLines) {
    override fun localPut(piece: Piece<MyPiece, Ground>, x: Int, y: Int, orientation: Int) {
        super.localPut(piece, x, y, orientation)
        println("PersistPhysicalBoard localPut ${piece.unit.containUnit.armedHero.baseHero.heroName.us}")
        val b = BattleUnit(piece.unit.containUnit.armedHero.baseHero.heroName.us, 1)
        val p = Positioning(piece.unit.containUnit.armedHero.baseHero.heroName.us, x, y, b)
        physicalBoard.unitList.add(p)
        repos.battleUnitRepository!!.save(b)
        repos.positioningRepository!!.save(p)
        repos.battleFieldRepository!!.save(physicalBoard)
    }

    override fun localMove(piece: Piece<MyPiece, Ground>, x: Int, y: Int, oldX: Int?, oldY: Int?, orientation: Int) {
        super.localMove(piece, x, y,oldX, oldY, orientation)
        println("PersistPhysicalBoard localMove ${piece.unit.containUnit.armedHero.baseHero.heroName.us}")
        physicalBoard.unitList.find { it.battleUnit?.id == piece.unit.containUnit.armedHero.baseHero.heroName.us }.also { it?.x = x;it?.y = y }
        //なくても保存されるはずなんだけどなんか勘違いしてるのかな…？
        repos.battleFieldRepository!!.save(physicalBoard)
        physicalBoard.ver = physicalBoard.ver + 1
        println("seq: ${physicalBoard.ver}")
    }

    override fun positionOf(piece: Piece<MyPiece, Ground>): Position? {
        val target = physicalBoard.unitList.find { it.battleUnit?.id == piece.unit.containUnit.armedHero.baseHero.heroName.us }
        return if (target == null) null else Position(target.x, target.y)
    }

    override fun getPiece(x: Int, y: Int): Piece<MyPiece, Ground>? {
//println("unit list")
//        physicalBoard.unitList.forEach { println("${it.id},${it.x},${it.y}") }

        val target = physicalBoard.unitList.find { it.x == x && it.y == y }
        if (target == null) return null
        //TODO:repository へのアクセスに
        val hero = StandardBaseHero.get(target.id!!)!!
        return MyPiece(jp.blogspot.turanukimaru.fehs.BattleUnit(ArmedHero(hero)), Board(6, 8, 1, this), Player())
    }
}