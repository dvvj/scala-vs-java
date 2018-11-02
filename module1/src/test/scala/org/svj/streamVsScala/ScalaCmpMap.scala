package org.svj.streamVsScala

object ScalaCmpMap extends App {

  import collection.JavaConverters._
  val employeeList = Employee.createTestList().asScala

  def traceMap[T, V](m:Map[T, V])(implicit ordering:Ordering[T]):Unit = {
    val tr = m.toList.sortBy(_._1).map(p => s"${p._1} -> ${p._2}")
      .mkString("\n")
    println(s"$tr\nMap size: ${m.size}")
  }

  val empNo2Emp = employeeList.map(e => e.empNo -> e).toMap
  println(s"----- Create Map: employee number -> employee")
  traceMap(empNo2Emp)

  val empNo2EmpWOAnn = empNo2Emp.filter(!_._2.firstName.equalsIgnoreCase("Ann"))
  println(s"----- Filtering Map: first name != 'Ann'")
  traceMap(empNo2EmpWOAnn)

}
