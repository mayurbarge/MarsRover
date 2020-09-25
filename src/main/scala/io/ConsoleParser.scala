package io

import terrain.{Direction, RoverCommand}

import scala.util.Try

trait Parser
case object ConsoleParser extends Parser {
  val consoleReader = scala.io.StdIn
  def split = consoleReader.readLine().split(" ")
  def read = consoleReader.readLine()

}

object Mappers {
  def map[A,B](try1: Try[A])(f: A => B):Try[B] = {
    for {
      e <- try1
    } yield f(e)
  }
  def lift[A,B](f: A => B): Try[A] => Try[B] = _ map (f)

  val mapToInt: String => Int = (x:String) => x.toInt
  val mapToDirection: String => Direction = x => Direction(x)
  val mapToRoverCommand: Char => RoverCommand = x => RoverCommand(x)

}