import sbt.Keys._
import sbt._

/**
 * Automatically enriches projects with the following settings (despite the word "override").
 */
object ProjectPlugin extends AutoPlugin {

  /**
   * Defines what members will be imported to the `build.sbt` scope.
   */
  val autoImport = ThingsToAutoImport

  /**
   * Thus plug-in will automatically be enabled; it has no requirements.
   */
  override def trigger: PluginTrigger = AllRequirements

  override val buildSettings: Seq[Setting[String]] = Seq(
    scalaVersion := "2.13.1"
  )

  object ThingsToAutoImport {

    implicit class ProjectOps(p: Project) {
      def withCats: Project =
        p.settings(libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.1")

      def withEffectMonad: Project =
        p.settings(libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.1")
    }
  }
}
