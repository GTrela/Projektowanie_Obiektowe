class Client
{
    long id;
    String name;
    String surname;

    public Client(long id, String name, String surname)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
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
}