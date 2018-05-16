package com.Tests.Unit;

import com.Git.Repository.Generics.Premade.Filters.JavaFilesOnly;
import com.Git.Repository.Pipelines.MergePipeline;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FiltersTest1
{
    @Test
    public void testFilters() throws GitAPIException, IOException {
        //https://github.com/MunGell/jphp

        MergePipeline pipeline = new MergePipeline("MunGell", "jphp");
        pipeline.filters().add(new JavaFilesOnly());
        File file = pipeline.GetMergedFile();


    }
}
