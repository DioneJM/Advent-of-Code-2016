import scala.annotation.tailrec
import scala.math.abs
import scala.io.Source

//Solution by /u/bahuljain on Reddit

case class Vec(u: Double, v: Double) {
  def rotateLeft: Vec = Vec(-v, u)
  def rotateRight: Vec = Vec(v, -u)
  def *(c: Double): Vec = Vec(c * u, c * v)
}

case class Point(x: Double, y: Double) {
  def +(dir: Vec): Point = Point(x + dir.u, y + dir.v)
  def dist(that: Point): Double = abs(x - that.x) + abs(y - that.y)
}

object Day1Part2 extends App {

  def path(start: Point, dir: Vec, size: Int): List[Point] =
    (0 until size).toList map { x => start + dir * x }

  @tailrec
  def getVisitedOrHQ(path: List[Point],
                     visited: Set[Point]): Either[Set[Point], Point] = path match {
    case Nil => Left(visited)
    case p :: rem_path =>
      if (visited contains p) Right(p)
      else getVisitedOrHQ(rem_path, visited + p)
  }

  @tailrec
  def followGuide(start: Point, dir: Vec,
                  guide: List[String], visited: Set[Point],
                  HQ: Option[Point]): (Point, Point) = guide match {
    case Nil => (start, HQ.getOrElse(Point(0, 0)))
    case step :: rest => {
      val size = step.tail.toDouble
      val new_dir = (step.head: @unchecked) match {
        case 'L' => dir.rotateLeft
        case 'R' => dir.rotateRight
      }
      //  part 1
      val dest = start + (new_dir * size)

      // part 2
      if (HQ.isDefined)
        followGuide(dest, new_dir, rest, visited, HQ)
      else {
        val pathPoints = path(start, new_dir, size.toInt)
        getVisitedOrHQ(pathPoints, visited) match {
          case Left(new_visited) =>
            followGuide(dest, new_dir, rest, new_visited, HQ)
          case Right(new_HQ) =>
            followGuide(dest, new_dir, rest, visited, Some(new_HQ))
        }
      }
    }
  }

  val input = Source.fromFile(args(0) + ".txt").getLines.mkString
  val guide = input.split(", ").toList
  val start = Point(0, 0)
  val res = followGuide(start, Vec(0, 1), guide, Set(), None)
  println("Distance from start to destination: " + res._1.dist(start))
  println("Distance from start to HQ: " + res._2.dist(start))
}