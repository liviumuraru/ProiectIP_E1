package com.tests.unit;

import com.Git.Repository.Generics.Premade.Filter.CHeaderFilesOnly;
import com.Git.Repository.Generics.Premade.Filter.TextFilesOnly;
import com.Git.Repository.MergePipeline;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public class MergePipelineTest
{
    public static void main(String... args)
    {
        MergePipeline pipeline = new MergePipeline("KhronosGroup", "OpenCL-Headers");
        pipeline.AddFilter(new CHeaderFilesOnly());
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
