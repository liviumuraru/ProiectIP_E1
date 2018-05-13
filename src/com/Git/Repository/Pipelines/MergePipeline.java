package com.Git.Repository.Pipelines;

import com.Git.Repository.Asset;
import com.Git.Repository.Assets;
import com.Git.Repository.Generics.Filtering.Filter;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MergePipeline
{
    private String user;
    private String repo;
    private String RepoURL;
    private Path DestinationDir;
    private String DestinationFileName;
    private FilterSettings filterData;

    public class FilterSettings
    {
        private List<Filter<Asset<File>>> filters;

        public FilterSettings()
        {
            filters = new ArrayList<>();
        }

        public void add(Filter<Asset<File>> filter)
        {
            filters.add(filter);
        }

        private List<Asset<File>> internal_execFilter(List<Asset<File>> files)
        {
            List<Asset<File>> filteredFiles = new ArrayList<>();

            for(Filter<Asset<File>> f : filters)
            {
                filteredFiles = f.accept(files);
            }

            return filteredFiles;
        }
    }

    public MergePipeline(String userName, String repoName)
    {
        filterData = new FilterSettings();
        user = userName;
        repo = repoName;
        RepoURL = "https://github.com/" + user + "/" + repo + ".git";
        DestinationDir = Paths.get("git/" + repo);
        DestinationFileName = "merged.txt";
    }

    public FilterSettings filters()
    {
        return filterData;
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

        // delegate filtering to filterData
        List<Asset<File>> filteredFiles = filterData.internal_execFilter(files);

        File merged = Assets.MergeFiles(filteredFiles, Paths.get(DestinationDir.toAbsolutePath().toString() + "/merged.txt"));

        return merged;
    }
}
