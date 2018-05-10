package com.company;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.*;

public class Main
{
    public static void main( String args[] )
    {
        List<String> keywords = new ArrayList<>();
        keywords.add("crawler");
        keywords.add("github");

        String language = "java";

        List<GHRepository> repositories = Crawler.getRepos(keywords, language);

        for(int i=0; i<repositories.size(); i++)
        {
            System.out.println(repositories.get(i).getName());
        }
    }
}
