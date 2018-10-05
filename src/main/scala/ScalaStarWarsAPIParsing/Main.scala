
import requests._

import scala.collection.mutable.ListBuffer

object Main extends App {

  def fetch(URL: String): Response = get(URL)

  val BaseURL: String = "https://swapi.co/api/people/"

  val MainPage: List[String] = fetch(BaseURL).data.text.split(",").toList
  val TotalPages = MainPage(0).split(":")(1)
  println(TotalPages)
  var ParsedData = new ListBuffer[String]()
  var TotalCount = ParsedData.length
  var x = 1
  while(TotalCount < TotalPages.toInt){
    val fetchedData = fetch(BaseURL++x.toString++"/")
    x+=1
    println(fetchedData.data.text)
    if (fetchedData.statusCode != 404) {
      ParsedData += fetchedData.data.text
      TotalCount = ParsedData.length
    }
  }
}