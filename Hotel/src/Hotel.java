import java.util.HashMap;
import java.util.List;
import java.io.*;


class Hotel
{
    public HashMap<Long, Room> Rooms;

    public void loadRooms(String path)
    {
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            Rooms = new HashMap<>();
            while ((line = br.readLine()) != null)
            {
                String[] roomParam = line.split(cvsSplitBy);
                if (roomParam.length == 3)
                {
                    Room room = new Room(Long.parseLong(roomParam[0]), Long.parseLong(roomParam[1]), roomParam[2]);
                    Rooms.put(Long.parseLong(roomParam[0]), room);
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    void saveRooms(String path)
    {
        StringBuilder builder = new StringBuilder();
        FileWriter outFile = null;
        try
        {
            outFile = new FileWriter(path);
            for (Room room : Rooms.values())
            {
                builder.append(room.getNr());
                builder.append(",");
                builder.append(room.getnOfBeds());
                builder.append(",");
                builder.append(room.getDescription());
                builder.append('\n');
            }
            outFile.write(builder.toString());
            outFile.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    void addRoom(long number, long nOfBeds, String description)
    {
        Room room = new Room(number, nOfBeds, description);
        Rooms.put(number, room);
    }

    void deleteRoom(long number)
    {
        Rooms.remove(number);
    }

    //rooms jest listą liczb określających ile osób chcemy zakwaterować w pokoju
    //np.: { 1, 2} oznacza, że potrzebujemy pokoju dla jednej osoby i drugiego pokoju dla dwu osób.
    List<ReservationInfo> findFreeRooms(Period period, List<Integer> rooms)
    {
        return null;
    }

    boolean makeReservation(Client client, ReservationInfo request)
    {
        return false;
    }
}