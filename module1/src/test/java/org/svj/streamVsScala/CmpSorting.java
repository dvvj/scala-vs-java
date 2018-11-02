package org.svj.streamVsScala;

import java.util.List;
import java.util.stream.Collectors;

public class CmpSorting {

    public static void main(String[] args) {
        List<Employee> employees = Employee.createTestList();

        System.out.println("---- By Employee Number");
        employees.sort(Employee.byEmpNo);
        Employee.trace(employees);

        List<Employee> sortedEmps;
//        System.out.println("---- By Employee Number (Stream)");
//        sortedEmps = employees.stream()
//            .sorted(Employee.byEmpNo)
//            .collect(Collectors.toList());
//        Employee.trace(sortedEmps);

        System.out.println("---- By Employee Number (Stream lambda)");
        sortedEmps = employees.stream()
            .sorted(
                (e1, e2) -> Integer.compare(e1.empNo, e2.empNo)
            )
            .collect(Collectors.toList());
        Employee.trace(sortedEmps);

        System.out.println("---- By Employee FirstName (Stream lambda)");
        sortedEmps = employees.stream()
            .sorted(
                (e1, e2) -> e1.firstName.compareTo(e2.firstName)
            )
            .collect(Collectors.toList());
        Employee.trace(sortedEmps);

        System.out.println("---- By Employee FirstName (Stream lambda case-insensitive)");
        sortedEmps = employees.stream()
            .sorted(
                (e1, e2) -> e1.firstName.toLowerCase().compareTo(e2.firstName.toLowerCase())
            )
            .collect(Collectors.toList());
        Employee.trace(sortedEmps);

    }
}
