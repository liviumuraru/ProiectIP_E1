package crawler.subset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllSubsets {

    public static List<List<String>> generateSubsets(List<String> elements){
        List<List<String>> subsets = new ArrayList<>(elements.size());
        for (int i = 1; i < (1<<elements.size()); i++)
        {
            List<String> subset = new ArrayList<>();
            for (int j = 0; j < elements.size(); j++)
                if ((i & (1 << j)) > 0)
                    subset.add(elements.get(j));
            subsets.add(subset);
        }
        subsets.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.size() - o2.size();
            }
        }.reversed());
        return subsets;
    }
}