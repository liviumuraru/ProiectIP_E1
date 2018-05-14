package com.company;

import org.kohsuke.github.GHRepository;

import java.util.*;

public class Crawler {

    private final static int MAX_REPOS = 10;

    public List<GHRepository> getAllReposList(List<String> keywords, String language) {
        List<GHRepository> repositories = new ArrayList<>();
        List<List<String>> allSubsets = AllSubsets.generateSubsets(keywords);

        for (List<String> subset : allSubsets) {
            if(repositories.size()==RepoCrawler.MAX_SIZE)
                break;
            repositories.addAll(RepoCrawler.getReposList(subset, language, repositories.size()));
        }
        return repositories;
    }


    public List<GHRepository> getRepos(List<String> keywords, String language, int filter) throws Exception {

        if(keywords.isEmpty())
            throw new Exception("No keywords were found.");
        if(language==null)
            throw new Exception("Language field cannot be empty.");

        List<GHRepository> repositories = new ArrayList<>(MAX_REPOS);
        List<GHRepository> repositoryList = getAllReposList(keywords, language);
        CriteriaSorter.sortByCriteria(repositoryList, filter);
        for (int i = 0; i < MAX_REPOS && i < repositoryList.size(); i++) {
            repositories.add(repositoryList.get(i));
        }
        return repositories;
    }

}