package manager;

import lucene.Repo;
import org.kohsuke.github.GHRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Trim follows the Singleton pattern
 */
public class Trim
{
    private static Trim single_instance = new Trim();
    private static Map<String, GHRepository > allProjects = new HashMap<>();
    private static Map<GHRepository, String> paths = new HashMap<>();
    private static List<String> remainingProjects = new ArrayList<>();

    /**
     * Private constructor restricted to this class itself
     */
    private Trim() {}

    /**
     * Static method to create instance of Singleton class
     * @return
     */
    public static Trim getInstance() {
        return single_instance;
    }

    /**
     * Adds path to the list of all paths
     * @param ghRepository
     * @param path
     */
    public static void addPath( GHRepository ghRepository, String path ) { paths.put( ghRepository, path ); }

    /**
     * Adds projects to the list of all projects
     * @param path
     * @param ghRepository
     */
    public static void add(String path, GHRepository ghRepository)
    {
        allProjects.put( path, ghRepository );
    }

    /**
     * Adds remaining projects
     * @param remainingProject
     */
    public static void addRemainingProject( String remainingProject )
    {
        remainingProjects.add( remainingProject );
    }

    /**
     * Getter for the list of all projects
     * @return
     */
    public static List<GHRepository> getRepos()
    {
        return new ArrayList<>( allProjects.values() );
    }

    /**
     * Getter for remaining projects
     * @return
     */
    public static List< Repo > getRemaining()
    {
        if(remainingProjects.isEmpty())
            return null;

        List<Repo> remaining = new ArrayList<>(  );
        for(String path : remainingProjects) {
            if(allProjects.containsKey( path ))
            {
                System.out.println( "!!" + path );
                remaining.add( new Repo(path, allProjects.get( path )) );
            }
        }
        return remaining;
    }

    /**
     * Getter for list of paths
     * @param ghRepositories
     * @return
     */
    public static List<String> getPaths( List<GHRepository> ghRepositories )
    {
        List<String> sortedPaths = new ArrayList<>();

        for ( GHRepository ghRepository : ghRepositories )
            if ( paths.containsKey( ghRepository ) )
                sortedPaths.add( paths.get( ghRepository ) );

        return sortedPaths;
    }
}

