import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Hotel hotel = new Hotel("out/production/Hotel/");
	    // create sample rooms
		hotel.addRoom(1,"Pokój testowy", Comfort.luksusowy);
	    hotel.addRoom(3,"Pokój testowy 2", Comfort.luksusowy);
	    hotel.addRoom(2,"Pokój zwykły 1", Comfort.standardowy);
	    hotel.addRoom(4,"Pokój zwykły 2", Comfort.standardowy);
	    hotel.addRoom(4,"Pokój zwykły 3", Comfort.standardowy);
	    for (Room room : hotel.getRooms().values())
        {
            System.out.printf("Room nr: %d, Beds number: %d, Description: %s, Comfort: %s\n",
                    room.getNr(),room.getnOfBeds(),room.getDescription(),room.getComfort());
        }

        //create sample clients
	    hotel.addClient("Jakub","Jas");
	    hotel.addClient("Grzegorz","Trela");

	    hotel.addSeasonalFee("sezon grzewczy",LocalDate.of(2017, Month.DECEMBER, 1),
			    LocalDate.of(2017, Month.DECEMBER, 31),1.5);

	    // sample reservation
	    Reservation test = hotel.checkReservation(1,LocalDate.of(2017, Month.DECEMBER, 20),
			    LocalDate.of(2017, Month.DECEMBER, 22),5);

	    long reservationId = hotel.addReservation(test);

	    test = hotel.getReservations().get(reservationId);

		System.out.println("Zarezerwowano pokoje o następujących id:");
		for (Long roomId : test.getRoomsList())
		{
			System.out.println(roomId);
		}
	    System.out.printf("Całkowita cena to: %.2f zł",test.totalPrice);


        //Map<Long, Room> test = hotel.selectRooms(hotel.getVacantRooms(LocalDate.of(2016, Month.NOVEMBER, 22), LocalDate.of(2016, Month.NOVEMBER, 25)), 2);

//        for(Map.Entry<Long,Room> entry : test.entrySet()) {
//            Long key = entry.getKey();
//            Room value = entry.getValue();
//
//            System.out.println(key + " => " + value.getnOfBeds());
//        }

        //System.out.print(hotel.selectRooms(hotel.getVacantRooms(LocalDate.of(2016, Month.NOVEMBER, 10), LocalDate.of(2016, Month.NOVEMBER, 25)), 4));
        /*Map<Long, Room> test = hotel.sortRoomsByBeds(hotel.getRooms());
        for(Map.Entry<Long,Room> entry : test.entrySet()) {
            Long key = entry.getKey();
            Room value = entry.getValue();

            System.out.println(key + " => " + value.getnOfBeds());
        }*/
        //System.out.print(hotel.getVacantRooms(LocalDate.of(2016, Month.NOVEMBER, 22), LocalDate.of(2016, Month.NOVEMBER, 25)));
        //System.out.print(hotel.isDateWithinRange(LocalDate.of(2016, Month.JUNE, 10), LocalDate.of(2016, Month.JUNE, 19), LocalDate.of(2016, Month.JUNE, 9)));
        //System.out.println(hotel.getVacantRooms());
        //hotel.addSeasonalFee("Sezon wakacyjny", LocalDate.of(0, Month.JUNE, 19), LocalDate.of(0, Month.SEPTEMBER, 1), 25);
        //hotel.saveSeasonalFees();
        //hotel.saveRooms();
        //hotel.addClient(1,"Jakub","Jas");
        //hotel.addClient(2,"Grzegorz","Trela");
        //hotel.saveClients();
	    /*List<Long> roomsIdList = new ArrayList<>();
	    roomsIdList.add(2L);
	    roomsIdList.add(20L);
	    roomsIdList.add(999L);


	    hotel.addReservation(2, LocalDate.of(2016, Month.NOVEMBER, 9),
			    LocalDate.of(2016, Month.NOVEMBER, 20), 1, roomsIdList);
	    hotel.saveReservations();*/


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
        hotel.saveConf();
    }
}
