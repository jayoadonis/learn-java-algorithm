package jayo.arb.learn_j.learn_java_algorithm_app;

import jayo.arb.learn_j.learn_java_algorithm_lib.sort.merge.MergeSort;
import jayo.arb.learn_j.learn_java_algorithm_lib.sort.merge.Strict;

import java.util.Arrays;

public class MainExe {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hi there from: learn-java-algorithm");

        final String[] strings = {"30", "-1", "#101", "0", "100", "9"};
        final String[] stringsI = {"xyz", "Abc", "abc", "Xyz"};
        final String[] stringsII = {"xyz", "Abc", "abc", "Xy"};
        final String[] stringsIII = {"xyz", "Abc", "A_bc", "Xyz", "v", ".b", ".a", "_" };
        final String[] stringsIV = {"30", "-1", "0", "100", "9"};
        final Integer[] ints = {30, -1, 0, 100, 9};

        final long begin = System.nanoTime();

        MergeSort<String> mergeSort = new MergeSort<String>(strings);
        MergeSort<String> mergeSortI = new MergeSort<String>(stringsI);
        MergeSort<String> mergeSortII = new MergeSort<String>(stringsII);
        MergeSort<String> mergeSortIII = new MergeSort<String>(stringsIII);
        MergeSort<Integer> mergeSortIV = new MergeSort<Integer>(
                Arrays.stream(stringsIV)
                        .map( v -> Integer.parseInt(v) )
                        .toArray( size -> new Integer[size] )
        );
        MergeSort<Integer> mergeSortV = new MergeSort<Integer>(
                ints
        );
        MergeSort<String> mergeSortVI = new MergeSort<String>(
                stringsIV
        );


        final long end = System.nanoTime();

        System.out.printf("Time taken: %f ns\n", (double) (end - begin));
        System.out.printf("Time taken: %f ms\n", (double) (end - begin) / 1_000_000.0);
        System.out.printf("Time taken: %f s\n", (double) (end - begin) / 1_000_000_000.0);

        System.out.println("is equal: " + mergeSort.equals(mergeSort));
        System.out.println("is equal: " + mergeSort.equals(mergeSortI));
        System.out.println("is equal: " + mergeSort.equals(mergeSortII));
        System.out.println("is equal::1 " + mergeSort.equals(mergeSortIII,
                new Strict<>(true, (x, y) ->
                        x.compareTo(
                                y
                        ) == 0
                )
        ));

        System.out.println("is equal::2 " + mergeSortV.equals(mergeSortIV,
                new Strict<>(true, (x, y) ->
                        x.compareTo(
                                y
                        ) == 0
                )
        ));

        mergeSort.sort(MergeSort::ascending);
        //REM: TODO-HERE; fix ClassCastException... when after sorting.
        System.out.println("is equal::3 " + mergeSortIV.equals(mergeSort,
                new Strict<Integer>(true, (Integer x, Integer y) ->
                        x.compareTo(
                                y
                        ) == 0
                )
        ));

        System.out.println(Arrays.toString(mergeSort.getItems()));
//        System.out.println(Arrays.toString(mergeSortIV.getItems()));
//        mergeSortIV.sort();
//        System.out.println(Arrays.toString(mergeSortIV.getItems()));
        //REM: TODO-HERE; fix ClassCastException... when after sorting.
        System.out.println("is equal::4 " + mergeSort.equals(mergeSortIV,
                new Strict<>(false, (x, y) ->
                        x.compareTo(
                                y
                        ) == 0
                )
        ));

        System.out.println(mergeSort);
        System.out.println(mergeSortI);
        System.out.println(mergeSortII);
        System.out.println(mergeSortIII);


    }
}
