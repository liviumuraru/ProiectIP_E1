package com.company;

import org.kohsuke.github.GHRepository;

import java.util.*;

public class Crawler {

    private final static int MAX_REPOS = 10;

    private List<GHRepository> getAllReposList(List<String> keywords, List<String> languages) {
        List<List<String>> allSubsets = AllSubsets.generateSubsets(keywords);
        List<GHRepository> repositories = new ArrayList<>();

        for (List<String> subset : allSubsets) {
            if(repositories.size()>=RepoCrawler.MAX_SIZE)
                break;
            repositories.addAll(RepoCrawler.getReposList(subset, languages, repositories.size()));
        }
        return repositories;
    }


    public List<GHRepository> getRepos(List<String> keywords, List<String> languages) throws CrawlerException {

        if(keywords.isEmpty()) {
            throw new CrawlerException("No keywords were found.");
        }
        if(languages.isEmpty()) {
            throw new CrawlerException("Language field cannot be empty.");
        }

        List<GHRepository> repositories = new ArrayList<>(MAX_REPOS);
        List<GHRepository> repositoryList = getAllReposList(keywords, languages);
        for (int i = 0; i < MAX_REPOS && i < repositoryList.size(); i++) {
            repositories.add(repositoryList.get(i));
        }
        return repositories;
    }

}
