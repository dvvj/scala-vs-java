package org.svj.streamVsScala;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmpMergeMap {
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
        List<Employee> dept1Employees = Employee.createTestList().stream()
            .filter(e -> !e.firstName.equalsIgnoreCase("Fred"))
            .map(e -> {
                e.empNo += 10000;
                return e;
            })
            .collect(Collectors.toList());
        List<Employee> dept2Employees = Employee.createTestList().stream()
            .filter(e -> !e.firstName.equalsIgnoreCase("Pam"))
            .filter(e -> !e.firstName.equalsIgnoreCase("Jack"))
            .map(e -> {
                e.empNo += 20000;
                return e;
            })
            .collect(Collectors.toList());

        Map<String, List<Employee>> dept1LastName2Emp = dept1Employees.stream()
            .collect(
                Collectors.groupingBy(emp -> emp.lastName)
            );
        Map<String, Integer> dept1LastNameCount = dept1LastName2Emp.entrySet().stream()
            .collect(
                Collectors.toMap(p -> p.getKey(), p -> p.getValue().size())
            );
        System.out.println("----- Dept 1 LastName count Map size: " + dept1LastNameCount.size());
        traceMap(dept1LastNameCount);

        Map<String, List<Employee>> dept2LastName2Emp = dept2Employees.stream()
            .collect(
                Collectors.groupingBy(emp -> emp.lastName)
            );
        Map<String, Integer> dept2LastNameCount = dept2LastName2Emp.entrySet().stream()
            .collect(
                Collectors.toMap(p -> p.getKey(), p -> p.getValue().size())
            );
        System.out.println("----- Dept 2 LastName count Map size: " + dept2LastNameCount.size());
        traceMap(dept2LastNameCount);

        Map<String, Integer> mergedLastNameCount = Stream.of(
            dept1LastNameCount, dept2LastNameCount
        ).map(Map::entrySet) // converts each map into an entry set
            .flatMap(Collection::stream) // converts each set into an entry stream, then
                                         //  "concatenates" it in place of the original set
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (a, b) -> a+b
                )
            );
        System.out.println("----- Dept 1/2 merged LastName count Map size: " + dept2LastNameCount.size());
        traceMap(mergedLastNameCount);
    }
}
