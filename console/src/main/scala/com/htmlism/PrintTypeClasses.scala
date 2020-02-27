package com.htmlism

import com.htmlism.conceptlattice.{ConceptLatticeEdge, Coordinate}
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
      .concatNel(rightChain)
      .prepend(applicativeLine)
      .prepend(monadLine)
}
