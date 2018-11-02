package org.svj.variance

object ContravarTest extends App {
  import org.svj.variance.TestClasses._

  type BaseFunc = Function1[Sub, Base]
  type SubFunc = Function1[Base, Sub]

  def subF:SubFunc = base => new Sub(base.v, -1)
  def baseF:BaseFunc = sub => new Base(sub.v)

  def testF(f:BaseFunc, param:Sub):Base = {
    f(param)
  }

  println("----- running base func")
  println(testF(baseF, new Sub(11, 22)))
  println("----- running sub func")
  println(testF(subF, new Sub(11, 22)))
}
