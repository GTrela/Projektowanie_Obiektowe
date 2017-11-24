import java.time.LocalDate;

class Reservation
{
    long id;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    long clientId;
    long roomNr;
    double totalPrice;

    public Reservation(long id, LocalDate checkInDate, LocalDate checkOutDate, long clientId, long roomNr, double totalPrice)
    {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.clientId = clientId;
        this.roomNr = roomNr;
        this.totalPrice = totalPrice;
    }

    public long getId()
    {
        return id;
    }

    public LocalDate getCheckInDate()
    {
        return checkInDate;
    }

    public LocalDate getCheckOutDate()
    {
        return checkOutDate;
    }

    public long getClientId()
    {
        return clientId;
    }

    public long getRoomNr()
    {
        return roomNr;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }
}