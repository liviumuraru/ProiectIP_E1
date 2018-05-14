package com.Tests.Unit;

import com.Git.Repository.Filtering.ORFilter;
import com.Git.Repository.Generics.Premade.Filters.CHeaderFilesOnly;
import com.Git.Repository.Generics.Premade.Filters.TextFilesOnly;
import com.Git.Repository.Pipelines.MergePipeline;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public class MergePipelineTest
{
    public static void main(String... args)
    {
        MergePipeline pipeline = new MergePipeline("marcobambini", "gravity");
        pipeline.filters().add(new ORFilter<>(new CHeaderFilesOnly(), new TextFilesOnly()));
        try
        {
            System.out.println(pipeline.GetMergedFile());
        }
        catch (GitAPIException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
