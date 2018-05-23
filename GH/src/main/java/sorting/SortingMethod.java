package sorting;

import lucene.Repo;
import org.kohsuke.github.GHRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class SortingMethod {
    public abstract List<GHRepository> sort(List<Repo> repositories);
}
