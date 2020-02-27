package com.htmlism

import cats.data._
import cats.implicits._

import com.htmlism.conceptlattice.{ConceptLatticeEdge, ConceptLatticeElement, ConceptLatticeNode, Coordinate}
import com.htmlism.conceptlattice.dsl._
import com.htmlism.svg._

object PrintTypeClasses extends App {
  val leftChain =
    EasyNode.chain(0, 0,
      EasyNode.attr("Functor"),
      EasyNode.attr("Applicative"),
      EasyNode.attr("Monad", "Option")
    )

  val rightChain =
    EasyNode.chain(1, 2,
      EasyNode.attr("ApplicativeError", "Validated"),
      EasyNode.attr("MonadError", "Decoder"),
      EasyNode.attr("Bracket"),
      EasyNode.attr("Sync", "IO"),
    )

  val applicativeLine = ConceptLatticeEdge(Coordinate(0, 1), Coordinate(1, 2))
  val monadLine = ConceptLatticeEdge(Coordinate(0, 2), Coordinate(1, 3))

  val typeClassLattice =
    leftChain
      .concat(rightChain)
      .prepend(applicativeLine)
      .prepend(monadLine)

  def render(factor: Int)(x: ConceptLatticeElement): NonEmptyChain[SvgElement] =
    x match {
      case ConceptLatticeEdge(Coordinate(x1, y1), Coordinate(x2, y2)) =>
        NonEmptyChain.one(SvgLine(x1 * 20, y1 * 20, x2 * 20, y2 * 20))

      case ConceptLatticeNode(Coordinate(x, y), attr, objs) =>
        NonEmptyChain.one(SvgCircle(x * 20, y * 20, 5))
    }

  println {
    typeClassLattice >>= render(20)
  }
}
