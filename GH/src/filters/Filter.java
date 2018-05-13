package filters;

import asset.Asset;

import java.io.File;
import java.util.List;

@FunctionalInterface
public interface Filter<T extends Asset >
{
    List<T> accept(List<T> input);
}