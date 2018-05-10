package com.Merge;

import com.Git.Repository.Asset;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesMerger
{
    public List<Asset<File>> files;

    public void merge(String location)
    {
        try
        {
            File mergedFiles = new File(location);
            mergedFiles.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFiles));

            for (Asset<File> fileAsset : files)
            {
                BufferedReader br = new BufferedReader(new FileReader(fileAsset.getAsset()));
                String text = null;
                bw.write(fileAsset.getAsset().getAbsolutePath());
                bw.newLine();
                while ((text = br.readLine()) != null)
                {
                    System.out.println(text);
                    bw.write(text);
                    bw.newLine();
                }
                br.close();
            }
            bw.close();

            //print merge file
            BufferedReader br = new BufferedReader(new FileReader(mergedFiles));
            String text = null;
            while ((text = br.readLine()) != null)
            {
                System.out.println(text);
//                bw.write(text);
//                bw.newLine();
            }

        }
        catch (Exception e )
        {
            e.printStackTrace();
        }

    }

    public FilesMerger()
    {
        files = new ArrayList<>();
    }

    public void AddFileAsset(Asset<File> fileAsset)
    {
        files.add(fileAsset);
    }
}
