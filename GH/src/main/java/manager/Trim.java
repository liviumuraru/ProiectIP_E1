package manager;

import lucene.Repo;
import org.kohsuke.github.GHRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trim
{
    private static Map<String, GHRepository > allProjects = new HashMap<>();
    private static List<String> remainingProjects = new ArrayList<>();

    public static void add(String path, GHRepository ghRepository)
    {
        allProjects.put( path, ghRepository );
    }

    public static void addRemainingProject( String remainingProject )
    {
        remainingProjects.add( remainingProject );
    }

    public static List< Repo > getRemaining()
    {
        if(remainingProjects.isEmpty())
            return null;

        List<Repo> remaining = new ArrayList<>(  );
        for(String path : remainingProjects) {
            if(allProjects.containsKey( path ))
            {
                System.out.println( path );
                remaining.add( new Repo(path, allProjects.get( path )) );
            }
        }
        return remaining;
    }
}
