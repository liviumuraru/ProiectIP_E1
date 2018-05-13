package com.Git.Repository;

import java.io.File;

public class Filters
{
    public static boolean hasFormat(String format, File file)
    {
        return file.getName().toLowerCase().endsWith(format);
    }
}
