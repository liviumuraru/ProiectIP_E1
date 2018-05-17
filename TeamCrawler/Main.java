package com.company;

import org.kohsuke.github.GHRepository;

import java.util.*;

public class Main
{
    public static void main( String args[] )
    {
        List<String> keywords = new ArrayList<>();
        keywords.add("crawler");
        keywords.add("github");

        List<String> languages = new ArrayList<>();
       // languages.add("java");
       // languages.add("c#");
        Crawler crawler = new Crawler();

        List<GHRepository> repositoryList = new ArrayList<>();
        try {
            repositoryList = crawler.getRepos(keywords, languages);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for(GHRepository repository : repositoryList){
            System.out.println(repository.getName() + " + " + repository.getSvnUrl());
        }
    }
}