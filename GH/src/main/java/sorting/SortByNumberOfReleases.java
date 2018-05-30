package sorting;

import lucene.Repo;
import org.kohsuke.github.GHRepository;

import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * This class provides the means to sort a list of repositories based on the number of releases
 */
public class SortByNumberOfReleases extends SortingMethod {

     /**
     * Returns a list of GHRepository objects sorted in descending order based on the number of releases.
     * a list of Repo objects (which contain a GHRepository and a path)
	 * @param repositories
     */
    @Override
    public List<GHRepository> sort(List<Repo> repositories){
        Map<GHRepository, Integer> map = new HashMap<>();

        for(Repo repo : repositories){
            try{
                map.put(repo.getGhRepository(),repo.getGhRepository().listReleases().asList().size());
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
                            entry.getKey().getFullName()+" releases "+entry.getValue()
                    ));

        }
        return new ArrayList<>(map.keySet());

    }
}
