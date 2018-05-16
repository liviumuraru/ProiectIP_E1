package com.Tests.Unit;

import com.Git.Repository.Filtering.ORFilter;
import com.Git.Repository.Generics.Premade.Filters.JavaFilesOnly;
import com.Git.Repository.Generics.Premade.Filters.TextFilesOnly;
import com.Git.Repository.Pipelines.MergePipeline;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FiltersTest1
{
    @Test
    public void TestJavaFilter() throws GitAPIException, IOException {
        //https://github.com/MunGell/jphp

        MergePipeline pipeline = new MergePipeline("MunGell", "jphp");
        pipeline.filters().add(new JavaFilesOnly());
        File file = pipeline.GetMergedFile();

        assert(file != null);
        assert (file.length() != 0);
    }

    @Test
    public void TestTextFilter() throws GitAPIException, IOException {
        MergePipeline pipeline = new MergePipeline("MunGell", "jphp");
        pipeline.filters().add(new TextFilesOnly());
        File file = pipeline.GetMergedFile();

        assert(file != null);
        assert (file.length() != 0);
    }

    @Test
    public void TestJavaAndTextFilters() throws GitAPIException, IOException {
        MergePipeline pipeline = new MergePipeline("MunGell", "jphp");
        pipeline.filters().add(new ORFilter<>(new TextFilesOnly(), new JavaFilesOnly()));
        File file = pipeline.GetMergedFile();

        assert(file != null);
        assert (file.length() != 0);
    }
}
