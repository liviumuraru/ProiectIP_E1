package filter.concreteFilters;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import asset.Asset;

class PyFilterTest {

	@Test
	void test() {
		List<Asset<File>> testFiles = new ArrayList<>();
		PyFilesOnly filter = new PyFilesOnly();
		
		for(String ext : PyFilesOnly.possibleExtensions) {
			Asset<File> asset = Asset.FromFile(new File("test." + ext));
			testFiles.add(asset);
			assert true == filter.accept(asset);
		}
		
		List<Asset<File>> filtered = filter.accept(testFiles);
		for(int i = 0; i < filtered.size(); i++) {
			assert filtered.get(i) == testFiles.get(i);
		}
	}
}
