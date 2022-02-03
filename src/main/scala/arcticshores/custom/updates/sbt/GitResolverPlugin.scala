package arcticshores.custom.updates.sbt

import arcticshores.custom.updates.GitLocalHandlerFactory
import sbt.AutoPlugin

import java.net.URL

class GitResolverPlugin extends AutoPlugin{

  URL.setURLStreamHandlerFactory(GitLocalHandlerFactory)


}
