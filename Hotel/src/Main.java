import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Hotel hotel = new Hotel("out/production/Hotel/");
        hotel.loadClients();
        hotel.loadRooms();
        hotel.loadReservations();
        hotel.loadSeasonalFees();
        //hotel.addSeasonalFee("Sezon wakacyjny", LocalDate.of(0, Month.JUNE, 19), LocalDate.of(0, Month.SEPTEMBER, 1), 25);
        //hotel.saveSeasonalFees();
        //hotel.addRoom(100,3,"Pokój z tarasem", Comfort.luksusowy);
        //hotel.addRoom(121,2,"Pokój zwykły", Comfort.standardowy);
        //hotel.saveRooms();
        //hotel.addClient(1,"Jakub","Jas");
        //hotel.addClient(2,"Grzegorz","Trela");
        //hotel.saveClients();
	    List<Long> roomsIdList = new ArrayList<>();
	    roomsIdList.add(new Long(2));
	    roomsIdList.add(new Long(20));
	    roomsIdList.add(new Long(999));


	    hotel.addReservation(1, LocalDate.of(2016, Month.NOVEMBER, 9),
			    LocalDate.of(2016, Month.NOVEMBER, 20), 1, roomsIdList);
	    hotel.saveReservations();
        /*for (Room room : hotel.getRooms().values())
        {
            System.out.printf("Room nr: %d, Beds number: %d, Description: %s, Comfort: %s\n",
                    room.getNr(),room.getnOfBeds(),room.getDescription(),room.getComfort());
        }*/

        /*for (Reservation reservation : hotel.getReservations().values())
        {
            System.out.printf("Reservation id: %d, Client id: %d, Room number: %d, Check-in date: %s, Check-out date: %s, Total price: %f\n",
                    reservation.getId(), reservation.getClientId(), reservation.getRoomNr(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getTotalPrice());
        }*/

        /*for (LocalDate date = LocalDate.of(2016, Month.NOVEMBER, 9);
             date.isBefore(LocalDate.of(2016, Month.NOVEMBER, 20)) || date.isEqual(LocalDate.of(2016, Month.NOVEMBER, 20));
             date = date.plusDays(1))
          {

            System.out.println(date);
          }*/
    }
}
