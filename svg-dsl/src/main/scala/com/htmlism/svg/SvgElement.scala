package com.htmlism.svg

sealed trait SvgElement

case class Circle(x: Int, y: Int, radius: Int) extends SvgElement

case class Line(x1: Int, y1: Int, x2: Int, y2: Int) extends SvgElement