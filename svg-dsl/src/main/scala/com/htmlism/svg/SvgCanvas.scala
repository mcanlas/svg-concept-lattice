package com.htmlism.svg

import cats.data.NonEmptyChain

case class SvgCanvas(height: Int, width: Int, xs: NonEmptyChain[SvgElement])

object SvgCanvas {
  def render(x: SvgCanvas): String =
    s"""<svg height="${x.height}" width="${x.width}">\n${x.xs.map(SvgElement.render).toNonEmptyList.toList.mkString("\n")}\n</svg>"""
}