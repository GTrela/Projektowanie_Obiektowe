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

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("\nData zameldowania: ");
		builder.append(checkInDate);
		builder.append("\nData wymeldowania: ");
		builder.append(checkOutDate);
		builder.append("\n\nZarezerwowane pokoje:\n");

        Hotel hotel = Hotel.getInstance();

        builder.append("\n");
        builder.append("Numer   Łóżka   Standard       Opis\n");

        for (long roomNr : roomsList)
        {
            builder.append(hotel.getRooms().get(roomNr).toString() + "\n");
        }

		builder.append(String.format("\nKwota do zapłaty %.2f zł\n",totalPrice));

		return builder.toString();
	}
}