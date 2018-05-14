package com.company;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AllSubsetsTest {

    @Test
    public void generateSubsets() {
        List<String> elements = new ArrayList<>();
        elements.add("a");
        elements.add("b");
        elements.add("c");
        AllSubsets instance = new AllSubsets();

        List<List<String>> expResult = new ArrayList<List<String>>();

        List<String> subset1 = new ArrayList<>();
        subset1.add("a");
        subset1.add("b");
        subset1.add("c");

        List<String> subset2 = new ArrayList<>();
        subset2.add("a");
        subset2.add("b");

        List<String> subset3 = new ArrayList<>();
        subset3.add("a");
        subset3.add("c");

        List<String> subset4 = new ArrayList<>();
        subset4.add("b");
        subset4.add("c");

        List<String> subset5 = new ArrayList<>();
        subset5.add("a");

        List<String> subset6 = new ArrayList<>();
        subset6.add("b");

        List<String> subset7 = new ArrayList<>();
        subset7.add("c");

        expResult.add(subset1);
        expResult.add(subset2);
        expResult.add(subset3);
        expResult.add(subset4);
        expResult.add(subset5);
        expResult.add(subset6);
        expResult.add(subset7);

        List<List<String>> result = instance.generateSubsets(elements);

        assertEquals(expResult, result);
    }
}