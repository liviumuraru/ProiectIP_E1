package com.Merge;

import java.io.*;
import java.util.ArrayList;

public class FilesMerger
{
    public ArrayList<File> files;

    public void merge(String location)
    {
        try
        {
            File mergedFiles = new File(location);
            mergedFiles.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFiles));

            for (File file : files)
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String text = null;
                bw.write(file.getAbsolutePath());
                bw.newLine();
                while ((text = br.readLine()) != null)
                {
//                    System.out.println(text);
                    bw.write(text);
                    bw.newLine();
                }
                br.close();
            }
            bw.close();

        }
        catch (Exception e )
        {
            e.printStackTrace();
        }

    }

    public FilesMerger(ArrayList<File> files)
    {
        this.files = files;
    }

    public ArrayList<File> getFiles()
    {

        return files;
    }

    public void setFiles(ArrayList<File> files)
    {
        this.files = files;
    }
}
