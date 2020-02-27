package com.htmlism.svg

import cats.data._

case class SvgCanvas(height: Int, width: Int, xs: Chain[SvgElement])

object SvgCanvas {
  def render(x: SvgCanvas): String =
    s"""<svg height="${x.height}" width="${x.width}">\n${x.xs.map(SvgElement.render).toList.mkString("\n")}\n</svg>"""
}