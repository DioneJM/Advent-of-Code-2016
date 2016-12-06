import scala.io.Source
object Day2Part1 {
  def main(args: Array[String]) {
    println("Day 3 solution")

    val triangleList = Source.fromFile(args(0) + ".txt").getLines.toArray

    for(triangle <- triangleList) {
      val list = triangle.split(" ")
      val s1 = triangle(0)
      val s2 = triangle(1)
      val s3 = triangle(2)
      println("s1 = " + s1)
      println("s2 = " + s2)
      println("s3 = " + s3)
    }
  }
}