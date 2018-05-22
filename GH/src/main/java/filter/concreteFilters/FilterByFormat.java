package filter.concreteFilters;

import java.io.File;

public class FilterByFormat
{

    private String format;

    public FilterByFormat( String format )
    {
        this.format = format;
    }

    public boolean accept(File pathname ) {
        return pathname.getName().toLowerCase().endsWith("." + format);
    }
}
