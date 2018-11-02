package org.svj.streamVsScala

object ScalaCmpMergeMap extends App {
  import collection.JavaConverters._

  val dept1Employees = Employee.createTestList().asScala
    .filter(!_.firstName.equalsIgnoreCase("Fred"))
    .map { e =>
      e.empNo += 10000
      e
    }
  val dept2Employees = Employee.createTestList().asScala
    .filter(!_.firstName.equalsIgnoreCase("Pam"))
    .filter(!_.firstName.equalsIgnoreCase("Jack"))
    .map { e =>
      e.empNo += 20000
      e
    }

  val dept1LastNameCount = dept1Employees.map(_.lastName -> 1)
    .groupBy(_._1)
    .mapValues(_.map(_._2).sum)
  println("----- Dept 1 LastName count:")
  ScalaCmpMap.traceMap(dept1LastNameCount)

  val dept2LastNameCount = dept2Employees.map(_.lastName -> 1)
    .groupBy(_._1)
    .mapValues(_.map(_._2).sum)
  println("----- Dept 2 LastName count:")
  ScalaCmpMap.traceMap(dept2LastNameCount)

  val mergedLastNameCount = (dept1LastNameCount.toList ++ dept2LastNameCount)
    .groupBy(_._1)
    .mapValues(_.map(_._2).sum)
  println("----- Dept 1/2 merged LastName count:")
  ScalaCmpMap.traceMap(mergedLastNameCount)

}
