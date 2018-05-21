package lucene;

public class Repo
{
    private String repoURL;
    private String destinationDIR;

    public Repo( String repoURL, String destinationDIR )
    {
        this.repoURL = repoURL;
        this.destinationDIR = destinationDIR;
    }

    public String getRepoURL()
    {
        return repoURL;
    }

    public String getDestinationDIR()
    {
        return destinationDIR;
    }

    @Override
    public String toString()
    {
        return this.repoURL + " *** " + destinationDIR;
    }
}
