class Room
{
	long nr;
	long nOfBeds;
	String description;
	Comfort comfort;

	public Room(long nr, long nOfBeds, String description, Comfort comfort)
	{
		this.nr = nr;
		this.nOfBeds = nOfBeds;
		this.description = description;
		this.comfort = comfort;
	}

	public Room(long nr, long nOfBeds, String description)
	{
		this.nr = nr;
		this.nOfBeds = nOfBeds;
		this.description = description;
		this.comfort = Comfort.standardowy;
	}

	public Room(long nr, long nOfBeds)
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

	public long getnOfBeds()
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
}