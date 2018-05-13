package com.Git.Repository;

import java.io.File;

public class Filters
{
    public static boolean hasFormat(String format, File file)
    {
        return file.getName().toLowerCase().endsWith(format);
    }

    /**
    ** @deprecated
     * COMPATIBILITY USAGE ONLY!
     * Use Filters.hasFormat(format, file) instead
     */
    public static boolean accept(File pathname)
    {
        return pathname.getName().toLowerCase().endsWith(".txt");
    }
}
