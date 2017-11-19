public class Main
{
    public static void main(String[] args)
    {
        Hotel hotel = new Hotel("out/production/Hotel/");
        //hotel.loadClients();
        hotel.loadRooms();
        //hotel.addRoom(100,3,"Testowy pokoj");
        //hotel.addRoom(121,2,"Testowy ziemniak");
        //hotel.saveRooms();
        //hotel.addClient(1,"Jakub","Jas");
        //hotel.addClient(2,"Grzegorz","Trela");
        //hotel.saveClients();

        for (Room room : hotel.getRooms().values())
        {
            System.out.printf("Room nr: %d, Beds number: %d, Description: %s, Comfort: %s\n",
                    room.getNr(),room.getnOfBeds(),room.getDescription(),room.getComfort());
        }
    }
}
