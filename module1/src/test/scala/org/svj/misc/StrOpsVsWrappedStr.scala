package org.svj.misc

import scala.collection.immutable.{StringOps, WrappedString}

object StrOpsVsWrappedStr extends App {

  val str1 = new WrappedString("H1")
  val str2 = new StringOps("H1")

  val v1 = str1 == str2
  val v2 = str2 == str1

  println(s"str1 == str2: $v1")
  println(s"str2 == str1: $v2")

}
