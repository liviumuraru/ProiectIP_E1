package asset;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Assets
{
    public static File MergeFiles( List< Asset<File> > fileList, Path destination, String repoURL, String destinationDir ) throws IOException {
        File mergedFiles = destination.toFile();
        mergedFiles.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFiles));

        bw.write( repoURL );
        bw.newLine();
        bw.write( Paths.get( destinationDir ).toString() );
        bw.newLine();

        for (Asset<File> fileAsset : fileList)
        {
            BufferedReader br = new BufferedReader(new FileReader(fileAsset.getAsset()));
            String text = null;

            bw.newLine();
            while ((text = br.readLine()) != null)
            {
                bw.write(text);
                bw.newLine();
            }
            br.close();
        }
        bw.close();

        return mergedFiles;
    }
}