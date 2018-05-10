package com.Git.Repository;

import com.Git.Repository.Generics.Filter;

import java.io.File;
import java.util.List;

public class FileFilter implements Filter<Asset<File>>
{
    @Override
    public List<Asset<File>> accept(List<Asset<File>> input) {
        return null;
    }
}
