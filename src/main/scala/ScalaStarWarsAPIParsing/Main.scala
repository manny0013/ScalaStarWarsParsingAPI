
import scalaj.http.{Http, HttpResponse}

object Main extends App {

  def fetch(URL: String): HttpResponse[String] = Http(URL).asString
  val BaseURL = "https://swapi.co/api/people/1/"

  println(fetch(BaseURL))
}