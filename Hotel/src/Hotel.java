import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Map;

class Hotel
{
    public Map<Long, Room> Rooms = new HashMap<>();
    public Map<Long, Client> Clients = new HashMap<>();
    private String path = "";

    void setPath(String path)
    {
        this.path = path;
    }

    void loadRooms()
    {
        String line = "";
        String cvsSplitBy = ",";
        String roomsPath = path + "rooms.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(roomsPath)))
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

    void saveRooms()
    {
        StringBuilder builder = new StringBuilder();
        String roomsPath = path + "rooms.csv";

        try (FileWriter outFile = new FileWriter(roomsPath))
        {
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
        }
        catch (IOException e)
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

    void loadClients()
    {
        String line = "";
        String cvsSplitBy = ",";
        String clientsPath = path + "clients.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(clientsPath)))
        {
            Clients = new HashMap<>();
            while ((line = br.readLine()) != null)
            {
                String[] clientParam = line.split(cvsSplitBy);
                if (clientParam.length == 3)
                {
                    Client client = new Client(Long.parseLong(clientParam[0]), clientParam[1], clientParam[2]);
                    Clients.put(Long.parseLong(clientParam[0]), client);
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void saveClients()
    {
        StringBuilder builder = new StringBuilder();
        String clientsPath = path + "clients.csv";

        try (FileWriter outFile = new FileWriter(clientsPath))
        {
            for (Client client : Clients.values())
            {
                builder.append(client.getId());
                builder.append(",");
                builder.append(client.getName());
                builder.append(",");
                builder.append(client.getSurname());
                builder.append('\n');
            }
            outFile.write(builder.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void addClient(long id, String name, String surname)
    {
        Client client = new Client(id, name, surname);
        Clients.put(id, client);
    }

    void deleteClient(long id)
    {
        Clients.remove(id);
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