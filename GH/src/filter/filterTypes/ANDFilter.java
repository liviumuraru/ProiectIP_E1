package filter.filterTypes;

import asset.Asset;
import filter.filterInterfaces.Filter;

import java.util.ArrayList;
import java.util.List;

public class ANDFilter<T extends Asset > extends CompositeFilter<T>
{
    public ANDFilter(Filter<T>... filters)
    {
        super(filters);
    }

    @Override
    public List<T> accept(List<T> input)
    {
        List<T> filteredList = new ArrayList<>();
        for(T elt : input)
        {
            boolean bPassed = true;

            for(Filter<T> filter : filters)
            {
                if(!filter.accept(elt))
                {
                    bPassed = false;
                    break;
                }
            }

            if(!bPassed) continue;
            filteredList.add(elt);
        }

        return filteredList;
    }

    @Override
    public boolean accept(T input)
    {
        for(Filter<T> filter : filters)
        {
            if(filter.accept(input) == false)
                return false;
        }

        return true;
    }
}