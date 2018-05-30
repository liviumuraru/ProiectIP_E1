package sorting;

import lucene.Repo;
import org.kohsuke.github.GHRepository;

import java.util.ArrayList;
import java.util.List;

/*
 * This abstract class lets you choose the desired sorting method
 */
public abstract class SortingMethod {
      /**
     * Returns a list of GHRepository objects sorted in an order specified by the classes extending this abstract class.
     * @param  repositories
     * a list of Repo objects (which contain a GHRepository and a path)
     */
    public abstract List<GHRepository> sort(List<Repo> repositories);
}
