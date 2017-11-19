class Room
{
    long nr;
    long nOfBeds;
    String description;

    public Room(long nr, long nOfBeds, String description)
    {
        this.nr = nr;
        this.nOfBeds = nOfBeds;
        this.description = description;
    }

    public long getNr()
    {
        return nr;
    }

    public long getnOfBeds()
    {
        return nOfBeds;
    }

    public String getDescription()
    {
        return description;
    }

}
