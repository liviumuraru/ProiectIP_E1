package lucene;

import org.kohsuke.github.GHRepository;

public class Repo
{
    private String destinationDIR;
    private GHRepository ghRepository;

    public Repo(String destinationDIR, GHRepository ghRepository)
    {
        this.ghRepository = ghRepository;
        this.destinationDIR = destinationDIR;
    }

    public GHRepository getGhRepository()
    {
        return ghRepository;
    }

    public String getDestinationDIR()
    {
        return destinationDIR;
    }

    @Override
    public String toString()
    {
        return this.ghRepository + " *** " + destinationDIR;
    }
}
