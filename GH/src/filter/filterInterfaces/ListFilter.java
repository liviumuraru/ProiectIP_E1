package filter.filterInterfaces;
import asset.Asset;

import java.util.List;

@FunctionalInterface
public interface ListFilter<T extends Asset >
{
    List<T> accept(List<T> input);
}
