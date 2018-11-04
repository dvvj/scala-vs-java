package org.svj.polymorph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenMethod {
    static <T> String gen1(T t) {
        return t.toString();
    }

    static <K extends Comparable<K>, V> void printSortedValues(Map<K, V> m) {
        m.entrySet().stream()
            .sorted(
                Comparator.comparing(Map.Entry::getKey)
            )
            .map(Map.Entry::getValue)
            .forEach(System.out::println);
    }
    public static void main(String[] args) {
        System.out.println(gen1(100));
        System.out.println(gen1("str"));

        Map<Integer, String> m1 = Stream.of(1, 3, 5, -1)
            .collect(
                Collectors.toMap(Function.identity(), i -> i.toString())
            );
        System.out.println("-------- Int -> String Map sorted values:");
        printSortedValues(m1);
        Map<Long, Long> m2 = Stream.of(1L, 3L, 5L, -1L)
            .collect(
                Collectors.toMap(Function.identity(), i -> (-i))
            );
        System.out.println("-------- Long -> Long Map sorted values:");
        printSortedValues(m2);

    }
}
