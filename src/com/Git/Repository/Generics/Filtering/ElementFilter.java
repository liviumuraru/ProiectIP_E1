package com.Git.Repository.Generics.Filtering;

import com.Git.Repository.Asset;

import java.util.List;

@FunctionalInterface
public interface ElementFilter<T extends Asset>
{
    public boolean accept(T input);
}