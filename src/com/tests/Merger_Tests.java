package com.tests;

import com.Merge.FilesMerger;

import java.io.File;
import java.util.ArrayList;

public class Merger_Tests
{
    public static void main(String args[])
    {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("C:\\Users\\Bogdan\\Desktop\\1.txt"));
        files.add(new File("C:\\Users\\Bogdan\\Desktop\\2.txt"));
        files.add(new File("C:\\Users\\Bogdan\\Desktop\\3.txt"));
        FilesMerger merger = new FilesMerger(files);
        merger.merge("D:\\merge.txt");
    }
}
