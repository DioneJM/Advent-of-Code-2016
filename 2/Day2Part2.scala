import scala.io.Source

//Not working
object Day2Part2 {
  def main(args: Array[String]) {
    println("Day 2 solution")
    var curButton = 5
    var password2 = ""

    val instructions  = Source.fromFile(args(0) + ".txt").getLines.toArray
    for(instr <- 0 to instructions.length - 1) { //iterate through all the instructions for each digit of the password
    var curInstruction = instructions(instr)
      for(c <- 0 to curInstruction.length - 1) { //Move through the keypad
        var direction = curInstruction(c)
        println("cur = " + curButton)

        direction match {
          case 'U' => {
            if(curButton == 3 || curButton == 13)
              curButton -= 2
            if(curButton == 6 || curButton == 7 || curButton == 8 || curButton == 10 || curButton == 11 || curButton == 12)
              curButton -= 4
          }
          case 'D' => {
            if(curButton == 1 || curButton == 11)
              curButton += 2
            if(curButton == 2 || curButton == 3 || curButton == 4 || curButton == 6 || curButton == 7 || curButton == 8)
              curButton += 4
          }
          case 'L' => {
            if(curButton != 1 || curButton != 2 || curButton != 5 || curButton != 10 || curButton != 13)
              curButton -= 1
          }
          case 'R' => {
            if(curButton != 1 || curButton != 4 || curButton != 9 || curButton != 12 || curButton != 13)
              curButton += 1
          }
        }
        print(direction + " -> " +curButton + " ")
      }
      println()
      println(curButton)
      curButton match {
        case 10 => password2 = password2 + 'A'
        case 11 => password2 = password2 + 'B'
        case 12 => password2 = password2 + 'C'
        case 13 => password2 = password2 + 'D'
        case default => password2 = password2 + curButton.toString
      }
    }
    println("Part 2 password = " + password2)
  }
}