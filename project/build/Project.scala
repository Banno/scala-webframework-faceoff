import sbt._

class Project(info: ProjectInfo) extends ParentProject(info) {
  lazy val unfilteredJettyExample = project("unfiltered-jetty", "Unfiltered Jetty Example", new UnfilteredJettyExample(_))
  lazy val unfilteredNettyExample = project("unfiltered-netty", "Unfiltered Netty Example", new UnfilteredNettyExample(_))
  lazy val sprayExample = project("spray", "Spray Example", new SprayExample(_))
  lazy val blueEyesExample = project("blueeyes", "Blue Eyes Example", new BlueEyesExample(_))
  lazy val mistExample = project("mist", "Mist Example", new MistExample(_))

  class UnfilteredJettyExample(info: ProjectInfo) extends DefaultProject(info) {
    val unfiltered_filter = "net.databinder" %% "unfiltered-filter" % "0.3.2"
    val unfiltered_jetty = "net.databinder" %% "unfiltered-jetty" % "0.3.2"
  }
  
  class UnfilteredNettyExample(info: ProjectInfo) extends DefaultProject(info) {
    val unfiltered_filter = "net.databinder" %% "unfiltered-netty" % "0.3.2"
    val unfiltered_netty = "net.databinder" %% "unfiltered-netty-server" % "0.3.2"

    lazy val repo = "jboss repo" at "http://repository.jboss.org/nexus/content/groups/public-jboss/"
  }
  
  class SprayExample(info: ProjectInfo)  extends DefaultWebProject(info) with AkkaProject {
    override val akkaActor  = akkaModule("actor")
    val akkaHttp            = akkaModule("http")
    val spray               = "cc.spray" %% "spray" % "0.5.0"

    val JETTY_VERSION = "8.0.0.M2"
    val specs       = "org.scala-tools.testing" %% "specs" % "1.6.7" % "test"
    val jettyServer = "org.eclipse.jetty" % "jetty-server" % JETTY_VERSION % "test"
    val jettyWebApp = "org.eclipse.jetty" % "jetty-webapp" % JETTY_VERSION % "test"
  }
    
  class BlueEyesExample(info: ProjectInfo) extends DefaultProject(info) {
    val sonatype_repo     = MavenRepository("Sonatype",     "http://oss.sonatype.org/content/repositories/releases/")
    val scala_tools_repo  = MavenRepository("Scala Tools",  "http://scala-tools.org/repo-snapshots/")
    val jboss_repo        = MavenRepository("JBoss",        "http://repository.jboss.org/nexus/content/groups/public/")
    val akka_repo         = MavenRepository("Akka",         "http://scalablesolutions.se/akka/repository/")

    val blueeyesRelease = "com.github.blueeyes" % "blueeyes" % "0.2.9" % "compile"
  }
  
  class MistExample(info: ProjectInfo) extends DefaultWebProject(info) with AkkaProject {
    override val akkaActor  = akkaModule("actor")
    val akkaHttp            = akkaModule("http")
    
    val JETTY_VERSION = "8.0.0.M2"
    val jettyServer = "org.eclipse.jetty" % "jetty-server" % JETTY_VERSION % "test"
    val jettyWebApp = "org.eclipse.jetty" % "jetty-webapp" % JETTY_VERSION % "test"
  }
}
