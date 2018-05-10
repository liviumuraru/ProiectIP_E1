package com.Git.Repository;

import com.Git.Repository.Generics.Filter;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MergePipeline
{
    private String user;
    private String repo;
    private String RepoURL;
    private Path DestinationDir;
    private String DestinationFileName;

    private List<Filter<Asset<File>>> filters;

    public MergePipeline(String userName, String repoName)
    {
        filters = new ArrayList<>();
        user = userName;
        repo = repoName;
        RepoURL = "https://github.com/" + user + "/" + repo + ".git";
        DestinationDir = Paths.get("git/" + repo);
        DestinationFileName = "merged.txt";
    }

    public void AddFilter(Filter<Asset<File>> filter)
    {
        filters.add(filter);
    }

    public File GetMergedFile() throws GitAPIException, IOException
    {

        List<Asset<File>> files = new ArrayList<>();

        if(!Files.exists(DestinationDir))
        {
            // Clone
            System.out.println("Pipeline: Cloning " + RepoURL + " into " + DestinationDir.toAbsolutePath());
            Git.cloneRepository()
                    .setURI(RepoURL)
                    .setDirectory(DestinationDir.toFile())
                    .call();
            System.out.println("Pipeline: Clone complete");
        }


        Object[] arr = Files.walk(DestinationDir).toArray();

        for(Object o : arr)
        {
            Path p = (Path)o;
            files.add(Asset.FromFile(p.toFile()));
        }
        // Filter the array
        for(Filter<Asset<File>> f : filters)
        {
            files = f.accept(files);
        }

        File merged = Assets.MergeFiles(files, Paths.get(DestinationDir.toAbsolutePath().toString() + "/merged.txt"));

        return merged;
    }
}
