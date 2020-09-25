package terrain

import terrain.Plateau.PlateauValidation

import scala.util.Try

trait Validation
case object ValidationSuccess extends Validation
case class ValidationFailed() extends Validation

trait ValidationErrors extends ValidationFailed
case class PlateauValidationError(errorMessage: String) extends ValidationErrors

trait Validation[T] {
  def run: T
}
case class PlateauValidator() {
  def run: (Plateau, Position) => Validation = (plateau: Plateau, position: Position) => {
    if(!(position.x < 0 || position.y < 0) && (position.x < plateau.upperRightX || position.y < plateau.upperRightY + x))
      ValidationSuccess
    else
      PlateauValidationError("Co-ordinates exceed plateau boundries")
  }
}

case class Position(x: Int, y: Int) {
  def forward(direction: Direction) = {
    direction match {
      case North => this.copy(y=y+1)
      case South => this.copy(y=y-1)
      case East => this.copy(x=x+1)
      case West => this.copy(x=x-1)
    }
  }
}


case class Rover(position: Position, direction: Direction) {

  def runCommand(roverCommand: RoverCommand) = {
    roverCommand match {
      case Left => this.copy(direction = direction.left)
      case Right => this.copy(direction = direction.right)
      case Move => this.copy(position = position.forward(this.direction))
    }
  }
}
case class Plateau(upperRightX: Int, upperRightY: Int, rovers: List[Rover])
object Plateau {
  type PlateauValidation = (Plateau, Position) => ValidationErrors
}


