package org.svj.polymorph

object Ex2NameParsing extends App {

  case class XmlNameSrc(
    foreName:String,
    lastName:String
    )

  case class CsvNameSrc(
    physicianFirstName:String,
    physicianMiddleName:String,
    physicianLastName:String
    )

  case class TxtNameSrc(
    fullName:String
    )

  case class NameParsed(
    firstName:String,
    middleName:Option[String],
    lastName:String
    )

  trait NameParsing[T] {
    def parse(t:T):NameParsed
  }

  implicit val XmlNameParsing:NameParsing[XmlNameSrc] = new NameParsing[XmlNameSrc] {
    override def parse(t: XmlNameSrc): NameParsed = {
      val foreNameParts = t.foreName.split("\\s+").filter(!_.isEmpty)
      val (firstName, middleName) =
        if (foreNameParts.length > 1)
          foreNameParts(0) -> Option(foreNameParts.tail.mkString(" "))
        else if (foreNameParts.length == 1)
          foreNameParts(0) -> None
        else {
          throw new IllegalArgumentException("Empty Names")
        }
      NameParsed(firstName, middleName, t.lastName)
    }
  }

  implicit val CsvNameParing:NameParsing[CsvNameSrc] = new NameParsing[CsvNameSrc] {
    override def parse(t: CsvNameSrc): NameParsed = {
      val middleName =
        if (t.physicianMiddleName.isEmpty) None
        else Option(t.physicianMiddleName)
      NameParsed(
        t.physicianFirstName,
        middleName,
        t.physicianLastName
      )
    }
  }

  implicit val TxtNameParing:NameParsing[TxtNameSrc] = new NameParsing[TxtNameSrc] {
    override def parse(t: TxtNameSrc): NameParsed = {
      val parts = t.fullName.split("\\s+").filter(!_.isEmpty)
      if (parts.length < 2)
        throw new IllegalArgumentException(s"At least 2 name parts required, actual: [$t]")
      else {
        val firstName = parts(0)
        val lastName = parts(parts.length-1)
        val middleParts = parts.slice(1, parts.length-1)
        val middleName =
          if (middleParts.isEmpty) None
          else Option(middleParts.mkString(" "))
        NameParsed(firstName, middleName, lastName)
      }
    }
  }

  def parseName[T](nameSrc:T)(implicit parser:NameParsing[T]):NameParsed = {
    println(s"---------- Parsing ${nameSrc.getClass.getSimpleName} ...")
    parser.parse(nameSrc)
  }

  println(
    parseName(
      XmlNameSrc(
        foreName = "John R",
        lastName = "Smith"
      )
    )
  )
  println(
    parseName(
      XmlNameSrc(
        foreName = "John",
        lastName = "Smith"
      )
    )
  )

  println(
    parseName(
      CsvNameSrc(
        physicianFirstName = "John",
        physicianMiddleName = "R",
        physicianLastName = "Smith"
      )
    )
  )
  println(
    parseName(
      CsvNameSrc(
        physicianFirstName = "John",
        physicianMiddleName = "",
        physicianLastName = "Smith"
      )
    )
  )

  println(
    parseName(
      TxtNameSrc(
        fullName = "John R Smith"
      )
    )
  )
  println(
    parseName(
      TxtNameSrc(
        fullName = "John Smith"
      )
    )
  )

//  val str:String = "123"
//  val i = str.toInt
//  val tmp = parseName(
//    TxtNameSrc(
//      fullName = "John R Smith"
//    )
//  )
//  tmp.parsed

//  implicit class ParseOps[A](val a:A) extends AnyVal {
//    def parsed(implicit evd:NameParsing[A]):NameParsed =
//      evd.parse(a)
//  }
//  val tmp = CsvNameSrc(
//    physicianFirstName = "John",
//    physicianMiddleName = "",
//    physicianLastName = "Smith"
//  )
//  tmp.parsed
//  println("---------- After adding ParseOps")
//  println(
//    XmlNameSrc(
//      foreName = "John R",
//      lastName = "Smith"
//    ).parsed
//  )
//  println(
//    CsvNameSrc(
//      physicianFirstName = "John",
//      physicianMiddleName = "R",
//      physicianLastName = "Smith"
//    ).parsed
//  )
//  println(
//    TxtNameSrc(
//      fullName = "John Smith"
//    ).parsed
//  )
}
