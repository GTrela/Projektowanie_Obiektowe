import java.time.LocalDate;
import java.util.List;

class Reservation
{
    long id;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    long clientId;
    List<Long> roomsList;
    double totalPrice;

    public Reservation(long id, LocalDate checkInDate, LocalDate checkOutDate, long clientId, double totalPrice, List<Long> roomsList)
    {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.clientId = clientId;
        this.roomsList = roomsList;
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

    public List<Long> getRoomsList()
    {
        return roomsList;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }
}