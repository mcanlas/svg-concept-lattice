package com.htmlism.svg

sealed trait SvgElement

case class SvgCircle(x: Int, y: Int, radius: Int) extends SvgElement

case class SvgLine(x1: Int, y1: Int, x2: Int, y2: Int) extends SvgElement