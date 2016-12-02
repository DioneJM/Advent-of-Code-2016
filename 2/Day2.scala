import scala.io.Source
object Day2Part1 {
  def main(args: Array[String]) {
    println("Day 2 solution")
    var curButton = 5
    var row = 2
    var col = 2
    var password1 = ""

    val instructions  = Source.fromFile(args(0) + ".txt").getLines.toArray
    for(instr <- 0 to instructions.length - 1) { //iterate through all the instructions for each digit of the password
      var curInstruction = instructions(instr)
      for(c <- 0 to curInstruction.length - 1) { //Move through the keypad
        var direction = curInstruction(c)
        direction match {
          case 'U' => {
            if(curButton > 3) { //Check if curButton is not in the first row
              curButton -= 3
            }
          }
          case 'D' => {
            if(curButton < 7) { //Check if curButton is not in the last row
              curButton += 3
            }
          }
          case 'L' => {
            if(curButton%3 != 1) { //Check if curButton is not in the first column
              curButton -= 1
            }
          }
          case 'R' => {
            if(curButton%3 != 0) { //Check if curButton is not in the last column
              curButton += 1
            }
          }
        }
      }
      password1 = password1 + curButton.toString
    }
    println("Part 1 password = " + password1)
  }
}