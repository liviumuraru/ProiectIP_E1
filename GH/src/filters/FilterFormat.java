package filters;

import java.io.File;
import java.io.FileFilter;

public class FilterFormat {

    private String format;

    public FilterFormat( String format )
    {
        this.format = format;
    }

    public boolean accept(File pathname ) {
        return pathname.getName().toLowerCase().endsWith("." + format);
    }
}
