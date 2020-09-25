package terrain

trait Direction {
  def left: Direction
  def right: Direction
}
case object North extends Direction {
  val left = West
  val right = East
}
case object South extends Direction {
  val left = East
  val right = West
}
case object East extends Direction {
  val left = North
  val right = South
}
case object West extends Direction {
  val left = South
  val right = North
}
object Direction {
  def apply(ch: String): Direction = {
    ch match {
      case "N" => North
      case "S" => South
      case "E" => East
      case "W" => West
    }
  }
}
