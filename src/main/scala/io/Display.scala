package io

trait Printer {
  def print(messages: String):Unit
}
case object ConsolePrinter extends Printer {
  def print(messages: String):Unit = println(messages)
}

object Messages {
  val askGrid = "Enter grid co-ordinates"
  val askRoverPosition = "Enter rover initial position"
  val askRoverCommands = "Enter rover commands"
}

object IO {
  def print(messages: String)(printer: Printer):Unit = printer.print(messages)
}

