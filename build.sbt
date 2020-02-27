lazy val root =
  Project("scala-papers", file("."))
    .withEffectMonad

lazy val `concept-lattice-dsl` =
  project
    .withCats

lazy val `svg-dsl` =
  project

lazy val console = project
  .dependsOn(`concept-lattice-dsl`, `svg-dsl`)