
import scalaj.http.Http

object Main extends App {
  val BaseURL = "https://swapi.co/api/people/"

  println(Http(BaseURL).asString)
}