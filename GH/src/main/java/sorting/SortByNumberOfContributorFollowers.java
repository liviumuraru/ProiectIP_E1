package sorting;
import lucene.Repo;
import org.kohsuke.github.GHRepository;

import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * This class provides the means to sort a list of repositories based on the overall number of followers/repository
 */
public class SortByNumberOfContributorFollowers extends SortingMethod {

     /**
     * Returns a list of GHRepository objects sorted in descending order based on the total number of the contributors' followers.
     * repositories - a list of Repo objects (which contain a GHRepository and a path)
     * @param repositories
     */
    @Override
    public List<GHRepository> sort(List<Repo> repositories){
        Map<GHRepository, Integer> map = new HashMap<>();
        for(Repo repo : repositories){
            try{
                List<GHRepository.Contributor> contributors= repo.getGhRepository().listContributors().asList();
                int followers=0;
                for(GHRepository.Contributor contributor:contributors){
                    followers+=contributor.getFollowersCount();
                }
                map.put(repo.getGhRepository(),followers);
            }
            catch(Exception e)
            {
                System.out.println("An error occurred: "+repo.getGhRepository().getFullName());
            }
        }
        map=map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1,e2) -> e1, LinkedHashMap::new));
        for (Map.Entry<GHRepository,Integer> entry : map.entrySet()) {
            System.out.println(
                    String.format(
                            "repository found: %s",
                            entry.getKey().getFullName()+" followers "+entry.getValue()
                    ));

        }
        return new ArrayList<>(map.keySet());
    }
}
