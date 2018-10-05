
import requests._
import scala.collection.mutable.ListBuffer
import org.json4s._
import org.json4s.jackson.JsonMethods._

object Main extends App {
  def fetch(URL: String): Response = get(URL)
  def jsonStrToMap(jsonStr: String): Map[String, Any] = {
    implicit val formats = org.json4s.DefaultFormats
    parse(jsonStr).extract[Map[String, Any]]
  }
  val BaseURL: String = "https://swapi.co/api/people/"
  val MainPage: List[String] = fetch(BaseURL).data.text.split(",").toList
  val TotalPages = MainPage(0).split(":")(1)
  println("Total Entries = "++TotalPages++"\n")
  var ParsedDataString = new ListBuffer[String]()
  var ParsedDataMap = new ListBuffer[Map[String,Any]]()
  var TotalCount = ParsedDataString.length
  var x = 1
  while(TotalCount < TotalPages.toInt){
    val fetchedData = fetch(BaseURL++x.toString++"/")
    x+=1
    if (fetchedData.statusCode != 404) {
      println("Fetched Data for "++fetchedData.data.text.split(",").toList(0).split(":")(1))
      ParsedDataString += fetchedData.data.text
      TotalCount = ParsedDataString.length
      ParsedDataMap += jsonStrToMap(fetchedData.data.text)
    }
  }
  println("\n\n"++
    """ Data available as Array of Dictionaries in
      | ParsedDataMap and as an Array of JSON strings
      | in ParsedDataString
    """.stripMargin
  )
  println("\n Printing List of JSON Strings")
  for(x <- 0 until TotalCount) println(ParsedDataString(x))
  println("\nPrinting the Dictionary/map of people... \n")
  for(x <- 0 until TotalCount) println(ParsedDataMap(x))
}