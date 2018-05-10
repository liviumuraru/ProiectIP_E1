package com.tests;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.nio.file.Paths;

public class Converter_Tests
{
    public static void main(String... args)
    {
        String userName = "KhronosGroup";
        String repoName = "OpenCL-Headers";

        String repoUrl = "https://github.com/" + userName + "/" + repoName + ".git";
        String cloneDirectoryPath = "git/projects/" + repoName;

        try {
            System.out.println("Cloning "+repoUrl+" into "+cloneDirectoryPath);
            Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                    .call();
            System.out.println("Completed Cloning");
        } catch (GitAPIException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
        }
    }
}
