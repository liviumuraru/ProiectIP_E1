package com.company;

import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class CriteriaSorter {
    public final static int BY_STARS = 1;
    public final static int BY_FOLLOWERS = 2;
    public final static int BY_CREATION_DATE = 3;
    public final static int BY_LAST_UPDATE_DATE = 4;
    public final static int BY_KEYWORDS = 5;

    public static void sortByCriteria(List<GHRepository> repositoryList, int filter) {
        switch (filter) {
            case BY_STARS:
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        return o1.getStargazersCount() - o2.getStargazersCount();
                    }
                }.reversed());
                break;
            case BY_FOLLOWERS:
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        return o1.getWatchers() - o2.getWatchers();
                    }
                }.reversed());
                break;
            case BY_CREATION_DATE:
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        try {
                            if (o1.getCreatedAt().getTime() - o2.getCreatedAt().getTime() < 0)
                                return -1;
                            else if (o1.getCreatedAt().getTime() - o2.getCreatedAt().getTime() == 0)
                                return 0;
                            else
                                return 1;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return -2;
                    }
                }.reversed());
                break;
            case BY_LAST_UPDATE_DATE:
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        try {
                            if (o1.getUpdatedAt().getTime() - o2.getUpdatedAt().getTime() < 0)
                                return -1;
                            else if (o1.getUpdatedAt().getTime() - o2.getUpdatedAt().getTime() == 0)
                                return 0;
                            else
                                return 1;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return -2;
                    }
                }.reversed());
                break;
            case BY_KEYWORDS: break;
            //TODO:addException for default case
            // default:
        }
    }
}
