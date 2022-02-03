package arcticshores.custom.updates

import java.net.{URLStreamHandler, URLStreamHandlerFactory}

object GitLocalHandlerFactory extends URLStreamHandlerFactory {
  override def createURLStreamHandler(scheme: String): URLStreamHandler = scheme match {
    case GitUrlHandler.LocalScheme => GitUrlHandler.local
    case _ => null
  }
}
