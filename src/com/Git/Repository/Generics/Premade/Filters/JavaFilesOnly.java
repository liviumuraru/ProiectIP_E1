package com.Git.Repository.Generics.Premade.Filters;

import com.Git.Repository.Asset;
import com.Git.Repository.Generics.Filtering.Filter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFilesOnly implements Filter<Asset<File>>
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


                if(extension.equals("java"))
                    newFiles.add(file);
            }
        }

        return newFiles;
    }

    @Override
    public boolean accept(Asset<File> file) {
        int indexOfDot = file.getAsset().getName().indexOf(".");
        if(!file.getAsset().isDirectory() && indexOfDot != -1)
        {
            String extension = file.getAsset().getName().substring(indexOfDot + 1,
                    file
                            .getAsset()
                            .getName()
                            .length());


            if(extension.equals("java"))
                return true;
        }

        return false;
    }
}