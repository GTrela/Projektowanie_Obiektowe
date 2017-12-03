class Room implements Comparable<Room>
{
	long nr;
	int nOfBeds;
	String description;
	Comfort comfort;

	public Room(long nr, int nOfBeds, String description, Comfort comfort)
	{
		this.nr = nr;
		this.nOfBeds = nOfBeds;
		this.description = description;
		this.comfort = comfort;
	}

	public Room(long nr, int nOfBeds, String description)
	{
		this.nr = nr;
		this.nOfBeds = nOfBeds;
		this.description = description;
		this.comfort = Comfort.standardowy;
	}

	public Room(long nr, int nOfBeds)
	{
		this.nr = nr;
		this.nOfBeds = nOfBeds;
		this.description = "";
		this.comfort = Comfort.standardowy;
	}

	public long getNr()
	{
		return nr;
	}

	public int getnOfBeds()
	{
		return nOfBeds;
	}

	public String getDescription()
	{
		return description;
	}

	public Comfort getComfort()
	{
		return comfort;
	}

	@Override
	public int compareTo(Room roomO)
	{
		return Long.compare(this.getnOfBeds(), roomO.getnOfBeds());
	}

	@Override
	public String toString()
	{
		return String.format("%-8d%-8d%-15s%-40s",nr,nOfBeds,comfort,description);
	}
}
