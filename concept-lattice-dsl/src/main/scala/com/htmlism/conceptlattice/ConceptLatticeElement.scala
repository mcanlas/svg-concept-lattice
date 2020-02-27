package com.htmlism.conceptlattice

sealed trait ConceptLatticeElement

case class ConceptLatticeNode(coord: Coordinate, attribute: Option[String], objects: List[String]) extends ConceptLatticeElement

case class ConceptLatticeEdge(xy1: Coordinate, xy2: Coordinate) extends ConceptLatticeElement
