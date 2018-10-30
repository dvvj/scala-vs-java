package org.svj.variance

object CovarTest extends App {

  case class Container[T](v:T)

  val c1:Container[String] = Container("abc")
  // do not compile:
  // val c2:Container[Any] = c1

  case class ContainerCov[+T](v:T)
  val c11:ContainerCov[String] = ContainerCov("abc")
  val c12:ContainerCov[Any] = c11 // compiles now
  println(c12)


}
