public class Main
{
    public static void main(String[] args)
    {
        Hotel hotel = new Hotel();
        hotel.setPath("out/production/Hotel/");
        hotel.loadClients();
        hotel.loadRooms();
        //hotel.addRoom(100,3,"Testowy pokoj");
        //hotel.addRoom(121,2,"Testowy ziemniak");
        //hotel.saveRooms();
        //hotel.addClient(1,"Jakub","Jas");
        //hotel.addClient(2,"Grzegorz","Trela");
        //hotel.saveClients();

        for (Room room : hotel.Rooms.values())
        {
            System.out.println(room.getNr() + " " + room.getnOfBeds() + " " + room.getDescription());
        }
    }
}
