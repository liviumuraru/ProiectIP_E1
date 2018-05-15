package filter.concreteFilters;

import asset.Asset;
import filter.filterInterfaces.Filter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PyFilesOnly implements Filter< Asset<File> >
{
    static final String[] possibleExtensions = { "py", "py3", "pyw", "pyx"};
    @Override
    public List<Asset<File>> accept(List<Asset<File>> input){
        List<Asset<File>> newFiles = new ArrayList<>();
        for( Asset<File> file : input){
            int indexOfDot = file.getAsset().getName().indexOf(".");
            if(!file.getAsset().isDirectory() && indexOfDot != -1){
                String extension = file.getAsset().getName().substring(indexOfDot + 1, file.getAsset().getName().length());
                System.out.println(extension);

                for(String ext : possibleExtensions) {
                    if(extension.equals(ext)) {
                        newFiles.add(file);
                        break;
                    }
                }
            }
        }

        return newFiles;
    }

    @Override
    public boolean accept(Asset<File> file) {
        int indexOfDot = file.getAsset().getName().indexOf(".");
        if(!file.getAsset().isDirectory() && indexOfDot != -1)
        {
            String extension = file.getAsset().getName().substring(indexOfDot + 1,
                    file
                            .getAsset()
                            .getName()
                            .length());

            System.out.println(extension);

            for(String ext : possibleExtensions)
                if(extension.equals(ext))
                    return true;
        }

        return false;
    }
}
