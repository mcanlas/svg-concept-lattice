package com.htmlism.conceptlattice
package dsl

import cats.data._

case class EasyNode(attribute: Option[String], objects: List[String])

object EasyNode {
  val empty: EasyNode = EasyNode(None, Nil)

  def attr(attribute: String, objects: String*): EasyNode =
    EasyNode(Some(attribute), objects.toList)

  def obj(objects: String*): EasyNode =
    EasyNode(None, objects.toList)

  def chain(x: Int, y: Int, n: EasyNode, ns: EasyNode*): NonEmptyChain[ConceptLatticeElement] = {
    val nodes =
      NonEmptyList
        .of(n, ns: _*)
        .zipWithIndex
        .map { case (elem, dy) =>
          ConceptLatticeNode(Coordinate(x, y + dy), elem.attribute, elem.objects)
        }

    val edges =
      (0 until y)
        .map(dy => ConceptLatticeEdge(Coordinate(x, dy), Coordinate(x, dy + 1)))
        .toList

    NonEmptyChain
      .fromNonEmptyList(nodes)
      .appendChain(Chain.fromSeq(edges))
  }
}