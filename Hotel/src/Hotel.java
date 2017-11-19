import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Hotel
{
	private Map<Long, Room> Rooms;
	private Map<Long, Client> Clients;
	private String path = "";

	public Hotel(String path)
	{
		this.path = path;
		Clients = new HashMap<>();
		Rooms = new HashMap<>();
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public void loadRooms()
	{
		String line = "";
		String cvsSplitBy = ",";
		String roomsPath = path + "rooms.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(roomsPath)))
		{
			while ((line = br.readLine()) != null)
			{
				String[] roomParam = line.split(cvsSplitBy);
				if (roomParam.length == 4)
				{
					Room room = new Room(Long.parseLong(roomParam[0]), Long.parseLong(roomParam[1]), roomParam[2], Comfort.valueOf(roomParam[3]));
					Rooms.put(Long.parseLong(roomParam[0]), room);
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveRooms()
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
				builder.append(",");
				builder.append(room.getComfort());
				builder.append('\n');
			}
			outFile.write(builder.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void addRoom(long number, long nOfBeds, String description, Comfort comfort)
	{
		Room room = new Room(number, nOfBeds, description, comfort);
		Rooms.put(number, room);
	}

	public void deleteRoom(long number)
	{
		Rooms.remove(number);
	}

	public void loadClients()
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

	public void saveClients()
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

	public void addClient(long id, String name, String surname)
	{
		Client client = new Client(id, name, surname);
		Clients.put(id, client);
	}

	public void deleteClient(long id)
	{
		Clients.remove(id);
	}

	//rooms jest listą liczb określających ile osób chcemy zakwaterować w pokoju
	//np.: { 1, 2} oznacza, że potrzebujemy pokoju dla jednej osoby i drugiego pokoju dla dwu osób.
	public List<ReservationInfo> findFreeRooms(Period period, List<Integer> rooms)
	{
		return null;
	}

	public boolean makeReservation(Client client, ReservationInfo request)
	{
		return false;
	}

	public Map<Long, Room> getRooms()
	{
		return Rooms;
	}

	public Map<Long, Client> getClients()
	{
		return Clients;
	}
}