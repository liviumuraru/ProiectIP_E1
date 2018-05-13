package com.Git.Repository.Generics.Filtering;

import com.Git.Repository.Asset;

import java.util.List;

@FunctionalInterface
public interface ListFilter<T extends Asset>
{
    public List<T> accept(List<T> input);
}