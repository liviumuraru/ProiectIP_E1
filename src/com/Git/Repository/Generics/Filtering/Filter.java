package com.Git.Repository.Generics.Filtering;

import com.Git.Repository.Asset;

import java.util.List;

public interface Filter<T extends Asset> extends ListFilter<T>, ElementFilter<T>
{

}
