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
        Crawler crawler = new Crawler();

        List<GHRepository> repositoryList = new ArrayList<>();
        try {
            repositoryList = crawler.getRepos(keywords, language, CriteriaSorter.BY_STARS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for(GHRepository repository : repositoryList){
            System.out.println(repository.getName() + " + " + repository.getSvnUrl());
        }
    }
}