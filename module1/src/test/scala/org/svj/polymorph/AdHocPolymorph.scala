package org.svj.polymorph

object AdHocPolymorph extends App {

  object ByImplicitConversion {
    trait Addable[A] {
      def add(a:A):A
    }

    class AddableInt(i:Int) extends Addable[Int] {
      override def add(a: Int): Int = i+a
    }

    class AddableStr(s:String) extends Addable[String] {
      override def add(a: String): String = s+a
    }

    class AddableList[T](l:List[T]) extends Addable[List[T]] {
      override def add(a: List[T]): List[T] = l ++ a
    }

    implicit def int2Addable(i:Int):AddableInt = new AddableInt(i)
    implicit def str2Addable(s:String):AddableStr = new AddableStr(s)
    implicit def lst2Addable[T](l:List[T]):AddableList[T] =
      new AddableList(l)

    def addItems[A](a:A, b:A)(implicit evidence:A => Addable[A]):A =
      a.add(b)
  }

//  import ByImplicitConversion._
//  println(addItems(1, 3))
//  println(addItems("1", "3"))
//  println(addItems(List(1), List(3)))

  object ByTypeClass {
    trait Addable[A] {
      def add(a:A, b:A):A
    }

    implicit val AddableInt:Addable[Int] = new Addable[Int] {
      override def add(a: Int, b: Int): Int = a+b
    }
    implicit val AddableStr:Addable[String] = new Addable[String] {
      override def add(a: String, b: String): String = a+b
    }
    implicit def AddableList[T]:Addable[List[T]] = new Addable[List[T]] {
      override def add(a: List[T], b: List[T]): List[T] = {
        a++b
      }
    }

    def addItems[A](a:A, b:A)(implicit evidence:Addable[A]):A =
      evidence.add(a, b)
  }
  import ByTypeClass._
  println(addItems(1, 3))
  println(addItems("1", "3"))
  println(addItems(List(1), List(3)))
}
