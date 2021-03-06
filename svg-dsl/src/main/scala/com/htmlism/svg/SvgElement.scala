package com.htmlism.svg

sealed trait SvgElement

case class SvgCircle(x: Int, y: Int, radius: Int) extends SvgElement

case class SvgLine(x1: Int, y1: Int, x2: Int, y2: Int) extends SvgElement

case class SvgText(x: Int, y: Int, s: String) extends SvgElement

object SvgElement {
  val render: SvgElement => String = {
    case SvgCircle(x, y, radius) =>
      emptyTag("circle", List(
        "stroke" -> "black",
        "stroke-width" -> "5",
        "fill" -> "red",
        "cx" -> x.toString,
        "cy" -> y.toString,
        "r" -> radius.toString))

    case SvgLine(x1, y1, x2, y2) =>
      emptyTag("line", List(
        "style" -> "stroke:rgb(255,0,0);stroke-width:2",
        "x1" -> x1.toString,
        "y1" -> y1.toString,
        "x2" -> x2.toString,
        "y2" -> y2.toString))

    case SvgText(x, y, s) =>
      startTag("text", List(
        "x" -> x.toString,
        "y" -> y.toString,
      )) + s + "</text>"
  }

  def emptyTag(name: String, attributes: List[(String, String)]): String =
    "<" + attributes
      .map { case (name, value) => s"""$name="$value"""" }
      .::(name)
      .appended("/>")
      .mkString(" ")

  def startTag(name: String, attributes: List[(String, String)]): String =
    "<" + attributes
      .map { case (name, value) => s"""$name="$value"""" }
      .::(name)
      .mkString(" ") + ">"
}