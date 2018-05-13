package com.Git.Repository.Filtering;

import com.Git.Repository.Asset;
import com.Git.Repository.Generics.Filtering.Filter;

import java.util.ArrayList;
import java.util.List;

public class ORFilter<T extends Asset> extends CompositeFilter<T>
{
    public ORFilter(Filter<T>... filters)
    {
        super(filters);
    }

    @Override
    public List<T> accept(List<T> input)
    {
        List<T> filteredList = new ArrayList<>();
        for(T elt : input)
        {
            for(Filter<T> filter : filters)
            {
                if(filter.accept(elt))
                {
                    filteredList.add(elt);
                    break;
                }
            }
        }

        return filteredList;
    }

    @Override
    public boolean accept(T input)
    {
        for(Filter<T> filter : filters)
        {
            if(filter.accept(input))
                return true;
        }

        return false;
    }
}
