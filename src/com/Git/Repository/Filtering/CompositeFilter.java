package com.Git.Repository.Filtering;

import com.Git.Repository.Asset;
import com.Git.Repository.Generics.Filtering.ElementFilter;
import com.Git.Repository.Generics.Filtering.Filter;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeFilter<T extends Asset> implements Filter<T>, ElementFilter<T>
{
    protected List<Filter<T>> filters;

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
