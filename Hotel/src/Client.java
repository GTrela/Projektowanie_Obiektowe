class Client
{
	long id;
	String name;
	String surname;
	int visitCount;

	public Client(long id, String name, String surname)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.visitCount = 0;
	}

	public Client(long id, String name, String surname, int visitCount)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.visitCount = visitCount;
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void incVisitCount()
	{
		++visitCount;
	}

	public void decVisitCount()
	{
		--visitCount;
	}

	public int getVisitCount()
	{
		return visitCount;
	}

	@Override
	public String toString()
	{
		return String.format("%-5s%-15s%-15s%-10s",id,name,surname,visitCount);
	}
}