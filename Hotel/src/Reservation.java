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

		builder.append(String.format("\nRezerwacja o id = %d, na klienta o id = %d. ",id,clientId));
		builder.append("Data zameldowania: ");
		builder.append(checkInDate);
		builder.append(" Data wymeldowania: ");
		builder.append(checkOutDate);
		builder.append("\nSklada sie z pokoi:\n");

        Hotel hotel = Hotel.getInstance();

		for (int i = 0; i < roomsList.size(); i++)
		{
			builder.append("-\t"+ hotel.getRooms().get(roomsList.get(i)).toString() + "\n");
		}

		builder.append(String.format("Kwota do zapłaty %.2f zł\n",totalPrice));

		return builder.toString();
	}
}