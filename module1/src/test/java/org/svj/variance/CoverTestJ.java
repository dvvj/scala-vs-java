package org.svj.variance;

import org.svj.variance.TestClasses.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoverTestJ {

    static void traceList(List<Base> l) {
        System.out.println("-------- traceList");
        l.stream().forEach(System.out::println);
    }
    static void traceArray(Base[] arr) {
        System.out.println("-------- traceArray");
        Arrays.asList(arr).forEach(System.out::println);
    }
    static void update(Base[] arr, int idx, Base newVal) {
        arr[idx] = newVal;
    }

    public static void main(String[] args) {
        Base b1 = new Base(1);
        Base b2 = new Base(2);


        List<Base> list1 = new ArrayList<>();
        list1.add(b1);
        list1.add(b2);
        traceList(list1);

//        List<Sub> list2 = new ArrayList<>();
        Sub s1 = new Sub(1, 11);
        Sub s2 = new Sub(2, 11);
        List<Base> list2 = new ArrayList<>();
        list2.add(s1);
        list2.add(s2);
        traceList(list2);

        Base[] arr1 = new Base[] { b1, b2 };
        Sub[] arr2 = new Sub[] { s1, s2 };

        traceArray(arr1);
        traceArray(arr2);

        update(arr1, 0, s1);
        traceArray(arr1);

//        // java.lang.ArrayStoreException
//        update(arr2, 0, new Sub2(3, "s33"));
//        traceArray(arr2);

    }
}
