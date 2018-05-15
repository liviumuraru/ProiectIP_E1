package filter.filterInterfaces;
import asset.Asset;


@FunctionalInterface
public interface ElementFilter<T extends Asset >
{
    boolean accept(T input);
}
