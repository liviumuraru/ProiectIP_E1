package filter.filterTypes;

import asset.Asset;
import filter.filterInterfaces.ElementFilter;
import filter.filterInterfaces.Filter;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeFilter<T extends Asset > implements Filter<T>, ElementFilter<T>
{
    protected List<Filter<T> > filters;

    public CompositeFilter(Filter<T>... filters_)
    {
        filters = new ArrayList<>();

        for(Filter<T> filter : filters_)
        {
            addFilter(filter);
        }
    }

    public void addFilter(Filter<T> filter)
    {
        filters.add(filter);
    }
}