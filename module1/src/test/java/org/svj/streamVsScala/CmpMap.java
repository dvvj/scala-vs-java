package org.svj.streamVsScala;

import scala.Int;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmpMap {
    static <T extends Comparable<T>, V> void traceMap(final Map<T, V> m) {
        StringBuilder b = new StringBuilder();

        m.keySet().stream()
            .sorted((t1, t2) -> t1.compareTo(t2))
            .forEach(k -> b.append(
                String.format("%s -> %s\n", k, m.get(k))
            ));
        System.out.print(b);
    }

    public static void main(String[] args) {
        List<Employee> employees = Employee.createTestList();

        System.out.println("----- Create Map: employee number -> employee");
        Map<Integer, Employee> empNo2Emp = employees.stream()
            .collect(
                Collectors.toMap(emp -> emp.empNo, Function.identity())
            );
        traceMap(empNo2Emp);
        System.out.println("Map size: " + empNo2Emp.size());

        Map<String, List<Employee>> lastName2Emp = employees.stream()
            .collect(
                Collectors.groupingBy(emp -> emp.lastName)
            );
        Map<String, Integer> lastNameCount = lastName2Emp.entrySet().stream()
            .collect(
                Collectors.toMap(
                    p -> p.getKey(), p -> p.getValue().size()
                )
            );
        System.out.println("----- LastName count Map size: " + lastNameCount.size());
        traceMap(lastNameCount);

    }
}
