package filter.concreteFilters;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import asset.Asset;

class CSFilterTest {

    @Test
    public void test()
    {
       List<Asset<File>> testFiles= new Vector<Asset<File>>();
	   CSFilesOnly filter = new CSFilesOnly();
	   
	   Asset<File> asset = Asset.FromFile(new File("test.cs"));
	   testFiles.add(asset);
	   assert true == filter.accept(asset);
	
	   List<Asset<File>> filtered =  filter.accept((testFiles));
	   for(int i=0; i<filtered.size() ;i++)
	   {
	       assert filtered.get(i) == testFiles.get(i);
	   }
    }

}
