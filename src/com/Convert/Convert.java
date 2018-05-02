package com.Convert;

public class Convert
{
    private Convert()
    {

    }

    static
    {
        //Instance = new Convert();
        Converter_GHRepository_to_Files = new GHRepositoryToFiles_Converter();
    }

    //public static Convert Instance;

    public static Converter Converter_GHRepository_to_Files;
}
