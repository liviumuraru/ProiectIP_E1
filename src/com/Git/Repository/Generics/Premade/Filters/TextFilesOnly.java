package com.Git.Repository.Generics.Premade.Filter;

import com.Git.Repository.Asset;
import com.Git.Repository.Generics.Filtering.Filter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TextFilesOnly implements Filter<Asset<File>>
{
    @Override
    public List<Asset<File>> accept(List<Asset<File>> input)
    {
        List<Asset<File>> newFiles = new ArrayList<>();
        for(Asset<File> file : input){
            int indexOfDot = file.getAsset().getName().indexOf
                    (".");
            if(!file.getAsset().isDirectory() && indexOfDot != -1)
            {
                String extension = file.getAsset().getName().substring(indexOfDot + 1,
                        file
                                .getAsset()
                                .getName()
                                .length());

                System.out.println(extension);

                if(extension.equals("txt"))
                    newFiles.add(file);
            }
        }

        return newFiles;
    }
}
