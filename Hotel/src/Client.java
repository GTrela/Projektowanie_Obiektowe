class Client
{
    long id;
    String name;
    String surname;
    int visitCount;
    String status;


    public Client(long id, String name, String surname)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.visitCount = 0;
        this.status = "client";
    }

    public Client(long id, String name, String surname, int visitCount, String status)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.visitCount = visitCount;
        this.status = status;
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

    public int getVisitCount()
    {
        return visitCount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setManager()
    {
        status = "manager";
    }

    public void setClient()
    {
        status = "client";
    }

    @Override
    public String toString()
    {
        return String.format("%s %s, Id: %d, Liczba wizyt: %d, Status: %s",
                name,surname,id,visitCount,status);
    }
}