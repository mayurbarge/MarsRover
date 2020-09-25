import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO
import io.{ConsoleParser, ConsolePrinter, IO, Mappers, Messages, Printer}
import terrain._

object MarsRover extends  App {

  loop()

  def loop() = {
    val y: () => Unit.type = () => Unit

    val plateauCoordinates= ConsoleParser.split.map(Mappers.mapToInt)
    val plateau = Plateau(plateauCoordinates.head, plateauCoordinates.last, List.empty)
    ConsoleDisplay.printMessage(Messages.askRoverPosition)
    val roverInputs = ConsoleParser.split
    val rover = Rover(Position(roverInputs.head.toInt, roverInputs.tail.head.toInt), Direction(roverInputs.last))
    ConsoleDisplay.printMessage(Messages.askRoverCommands)
    val roverCommands: Seq[RoverCommand] =ConsoleParser.read.map(Mappers.mapToRoverCommand)
    val roverWhenStopped: Rover = roverCommands.foldLeft(rover: Rover)((acc:Rover, e) => {
      val originalPosition = acc.position
      val validation = PlateauValidator().run
      validation(plateau, originalPosition) match {
        case ValidationSuccess => acc.runCommand(e)
        case PlateauValidationError(msg) =>
      }



    })


    ConsoleDisplay.printRover(roverWhenStopped)
  }
}
