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
}