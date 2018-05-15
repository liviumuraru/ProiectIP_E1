package filter.filterInterfaces;

import asset.Asset;

public interface Filter<T extends Asset> extends ListFilter<T>, ElementFilter<T>
{

}