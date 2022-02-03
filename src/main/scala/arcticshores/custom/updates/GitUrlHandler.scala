package arcticshores.custom.updates

import org.eclipse.jgit.api.Git

import java.io.{ByteArrayInputStream, File, IOException, InputStream}
import java.net.{URL, URLConnection, URLStreamHandler}
import java.nio.charset.StandardCharsets
import scala.jdk.CollectionConverters.collectionAsScalaIterableConverter


class GitLocalUrlConnection(url: URL) extends URLConnection(url: URL){
  override def connect(): Unit = ()

  private def repoLocation = url.getPath

  private def readTag: String = {
    val directory = new File(repoLocation)
    if (!directory.isDirectory)
      throw new IOException()
    val git = Git.init().setDirectory(new File(repoLocation)).call()

    git.tagList().call().asScala.headOption.map(_.getName).getOrElse("")
  }

  override def getInputStream: InputStream = new ByteArrayInputStream(
    content(readTag).getBytes(StandardCharsets.UTF_8)
  )


  def content(version: String) =
    s"""<metadata>
          <versioning>
            <versions>
              <version>
                ${sane(version)}
              </version>
            </versions>
          </versioning>
       </metadata>"""

  def sane(str: String) = str.filter(
    c =>
      c >= 'a' && c <= 'z' ||
        c >= 'A' && c <= 'Z' ||
        c >= '0' && c <= '9' ||
        c == '.' || c == '-' || c == '_'
  )

}

class GitLocalHandler extends URLStreamHandler {
  override def openConnection(url: URL): URLConnection = new GitLocalUrlConnection(url)
}

object GitUrlHandler {
  val RemoteScheme = "git"
  val LocalScheme = "git-local"

  val local: URLStreamHandler = new GitLocalHandler
}