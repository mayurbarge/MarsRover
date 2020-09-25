package terrain

trait RoverCommand
case object Left extends RoverCommand
case object Right extends RoverCommand
case object Move extends RoverCommand

object RoverCommand {
  def apply(ch: Char): RoverCommand = {
    ch match {
      case 'L' => Left
      case 'R' => Right
      case 'M' => Move
    }
  }
}