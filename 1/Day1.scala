import scala.io.Source

object Day1 {
  object Direction extends Enumeration {
    val North = 1
    val East = 2
    val South = 3
    val West = 4
  }

  def main(args: Array[String]) {
    println("Day 1 solution")

    val instructions = Source.fromFile(args(0) + ".txt").getLines.mkString.split(',').map(_.trim.splitAt(1)).toArray
    var xDist = 0
    var yDist = 0
    var curDir = Direction.North

    for(i <- 0 to instructions.length - 1) {
      val rotation = instructions(i)._1
      val curSteps = instructions(i)._2.toInt

      curDir match {
        case Direction.North => {
          if(rotation == "R") {
            curDir = Direction.East
            xDist += curSteps
          } else {
            curDir = Direction.West
            xDist -= curSteps
          }
        }
        case Direction.East => {
          if(rotation == "R") {
            curDir = Direction.South
            yDist -= curSteps
          } else {
            curDir = Direction.North
            yDist += curSteps
          }
        }
        case Direction.South => {
          if(rotation == "R") {
            curDir = Direction.West
            xDist -= curSteps
          } else {
            curDir = Direction.East
            xDist += curSteps
          }
        }
        case Direction.West => {
          if(rotation == "R") {
            curDir = Direction.North
            yDist += curSteps
          } else {
            curDir = Direction.South
            yDist -= curSteps
          }
        }
      }
    }
    val totalDist = xDist.abs + yDist.abs
    println(xDist +" , " + yDist)
    println("Total Distance = " + totalDist)
  }
}
