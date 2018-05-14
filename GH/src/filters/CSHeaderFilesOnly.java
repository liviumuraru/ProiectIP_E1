package filters;

import java.util.List;
import asset.Asset;

import java.io.File;
import java.util.ArrayList;

public class CSHeaderFilesOnly implements Filter<Asset <File>>
{
    @Override
    public List<Asset<File>> accept(List<Asset<File>> input)
    {
        List<Asset<File>> newFiles = new ArrayList<>();
        for(Asset<File> file : input){
            int indexOfDot = file.getAsset().getName().indexOf
                    (".");
            if(!file.getAsset().isDirectory() && indexOfDot != -1)
            {
                String extension = file.getAsset().getName().substring(indexOfDot + 1,
                        file
                                .getAsset()
                                .getName()
                                .length());

                System.out.println(extension);

                if(extension.equals("cs"))
                    newFiles.add(file);
            }
        }

        return newFiles;
    }
}
