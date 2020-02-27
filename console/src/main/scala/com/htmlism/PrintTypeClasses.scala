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
    )

  val effectChain =
    EasyNode.chain(2, 4,
      EasyNode.attr("Bracket"),
      EasyNode.attr("Sync", "IO"),
    )

  val leftToError =
    NonEmptyChain(
      ConceptLatticeEdge(Coordinate(0, 1), Coordinate(1, 2)),
      ConceptLatticeEdge(Coordinate(0, 2), Coordinate(1, 3)))

  val errorToEffect =
    NonEmptyChain(
      ConceptLatticeEdge(Coordinate(1, 3), Coordinate(2, 4)))

  val typeClassLattice =
    leftChain ++ rightChain ++ effectChain ++ leftToError ++ errorToEffect

  private def render(xFlex: Int, yFlex: Int, xPad: Int, yPad: Int)(x: ConceptLatticeElement) =
    x match {
      case ConceptLatticeEdge(Coordinate(x1, y1), Coordinate(x2, y2)) =>
        (
          Chain.one(SvgLine(xPad + x1 * xFlex, yPad + y1 * yFlex, xPad + x2 * xFlex, yPad + y2 * yFlex)),
          Chain.empty[SvgElement],
          Chain.empty[SvgElement]
        )

      case ConceptLatticeNode(Coordinate(x, y), attr, objs) =>
        val attrChain =
          attr.fold(Chain.empty[SvgElement]){ s => Chain.one(SvgText(xPad + x * xFlex, yPad + y * yFlex, s)) }

        val objChain =
          objs.headOption.fold(Chain.empty[SvgElement]){ s => Chain.one(SvgText(xPad + x * xFlex, 20 + yPad + y * yFlex, s)) }

        (
          Chain.empty[SvgElement],
          Chain.one(SvgCircle(xPad + x * xFlex, yPad + y * yFlex, 30)),
          attrChain ++ objChain,
        )
    }

  val (layerOne, layerTwo, layerThree) =
    typeClassLattice
      .map(render(150, 100, 50, 50))
      .reduce

  println {
    SvgCanvas.render {
      SvgCanvas(1000, 1000, layerOne ++ layerTwo ++ layerThree)
    }
  }
}
