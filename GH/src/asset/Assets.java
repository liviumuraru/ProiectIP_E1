package asset;


import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class Assets
{
    public static File MergeFiles( List< Asset<File> > fileList, Path destination ) throws IOException {
        File mergedFiles = destination.toFile();
        mergedFiles.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFiles));

        for (Asset<File> fileAsset : fileList)
        {
            BufferedReader br = new BufferedReader(new FileReader(fileAsset.getAsset()));
            String text = null;
            bw.write(fileAsset.getAsset().getAbsolutePath());
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