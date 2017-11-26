import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Hotel
{
	private Map<Long, Room> Rooms;
	private Map<Long, Client> Clients;
	private Map<Long, Reservation> Reservations;
	private Map<String, SeasonalFee> SeasonalFees;
	private String path = "";
	private long reservationNumber;
	private long roomNumber;
	private long clientNumber;

	public Hotel(String path)
	{
		setPath(path);
		Clients = new HashMap<>();
		Rooms = new HashMap<>();
		Reservations = new HashMap<>();
		SeasonalFees = new HashMap<>();
		Init();
	}

	public void Init ()
	{
		reservationNumber = 1;
		roomNumber = 1;
		clientNumber = 1;
//		loadClients();
//		loadRooms();
//		loadReservations();
//		loadSeasonalFees();
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	// Room management
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

	public long addRoom (long nOfBeds, String description, Comfort comfort)
	{
		Room room = new Room(roomNumber, nOfBeds, description, comfort);
		Rooms.put(roomNumber, room);
		return roomNumber++;
	}

	public void deleteRoom(long number)
	{
		Rooms.remove(number);
	}

	// Client management
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

	public long addClient(String name, String surname)
	{
		Client client = new Client(clientNumber, name, surname);
		Clients.put(clientNumber, client);
		return clientNumber++;
	}

	public void deleteClient(long id)
	{
		Clients.remove(id);
	}

	// Reservation management
	public void loadReservations()
	{
		String line = "";
		String cvsSplitBy = ",";
		String reservationsPath = path + "reservations.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(reservationsPath)))
		{
			Reservations = new HashMap<>();
			while ((line = br.readLine()) != null)
			{
				String[] reservationParam = line.split(cvsSplitBy);
				if (reservationParam.length > 5)
				{
					List<Long> roomsIdList = new ArrayList<>();
					for (int i = 5; i < reservationParam.length; i++)
					{
						roomsIdList.add(Long.parseLong(reservationParam[i]));
					}

					Reservation reservation = new Reservation(Long.parseLong(reservationParam[0]), LocalDate.parse(reservationParam[1]),
							LocalDate.parse(reservationParam[2]), Long.parseLong(reservationParam[3]), Double.parseDouble(reservationParam[4]), roomsIdList);
					Reservations.put(Long.parseLong(reservationParam[0]), reservation);
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveReservations()
	{
		StringBuilder builder = new StringBuilder();
		String reservationsPath = path + "reservations.csv";

		try (FileWriter outFile = new FileWriter(reservationsPath))
		{
			for (Reservation reservation : Reservations.values())
			{
				builder.append(reservation.getId());
				builder.append(",");
				builder.append(reservation.getCheckInDate());
				builder.append(",");
				builder.append(reservation.getCheckOutDate());
				builder.append(",");
				builder.append(reservation.getClientId());
				builder.append(",");
				builder.append(reservation.getTotalPrice());
				for (Long item : reservation.getRoomsList())
				{
					builder.append(",");
					builder.append(item);
				}
				builder.append('\n');
			}
			outFile.write(builder.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Map<Long, Room> sortRoomsByBeds(Map<Long, Room> roomsToSort)
	{
		List<Map.Entry<Long, Room>> list = new LinkedList<>(roomsToSort.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Long, Room>>()
		{
			public int compare(Map.Entry<Long, Room> o1, Map.Entry<Long, Room> o2)
			{
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<Long, Room> sortedMap = new LinkedHashMap<>();

		for (Map.Entry<Long, Room> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public boolean isDateWithinRange(LocalDate startDate, LocalDate endDate, LocalDate dateToCheck)
	{
		if (startDate.isAfter(endDate))
		{
			LocalDate temp = startDate;
			startDate = endDate;
			endDate = temp;
		}
		return !(dateToCheck.isBefore(startDate) || dateToCheck.isAfter(endDate));
	}

	public List<Long> getVacantRooms(LocalDate startDate, LocalDate endDate)
	{
		List<Long> rooms = new ArrayList<>(Rooms.keySet());

		for (Reservation reservation : Reservations.values())
		{
			LocalDate checkIn = reservation.getCheckInDate();
			LocalDate checkOut = reservation.getCheckOutDate();

			if (isDateWithinRange(checkIn, checkOut, startDate) || isDateWithinRange(checkIn, checkOut, endDate))
			{
				List<Long> resRooms = new ArrayList<>(reservation.getRoomsList());

				for (Long roomNr : resRooms)
				{
					if (rooms.contains(roomNr))
					{
						rooms.remove(roomNr);
					}
				}
			}
		}

		return rooms;
	}

	public Map<Long, Room> selectRooms(List<Long> roomsList, long nOfBeds)
	{
		Map<Long, Room> tempRooms = new HashMap<>();
		Map<Long, Room> selectedRooms = new HashMap<>();
		long beds = nOfBeds;

		for (Long roomNr : roomsList)
		{
			tempRooms.put(roomNr, Rooms.get(roomNr));
		}

		tempRooms = sortRoomsByBeds(tempRooms);

		for (Map.Entry<Long, Room> entry : tempRooms.entrySet())
		{
			Long availableBeds = entry.getValue().getnOfBeds();

			if (beds > 0)
			{
				selectedRooms.put(entry.getKey(), entry.getValue());
				beds -= availableBeds;
			}
		}

		return selectedRooms;
	}

	private double calculateTotalPrice(LocalDate checkInDate, LocalDate checkOutDate, long clientId, List<Long> roomsList)
	{
		double dailyPrice = 50.0; // price for 1 bed in basic room
		double totalSum = 0.0;

		long clientVisitCount = Clients.get(clientId).visitCount;

		// calculate price for list of rooms for every day
		for (LocalDate i = checkInDate; i.until(checkOutDate).getDays() >= 0; i = i.plusDays(1) )
		{
			double oneDayPrice = 0.0;
			double seasonFee = 1.0;
			// check if there is higher price in season
			for (SeasonalFee extraFee : SeasonalFees.values())
			{
				 if(isDateWithinRange(extraFee.getStartDate(),extraFee.getEndDate(), i))
				 {
				 	seasonFee = extraFee.getFee();
				 }
			}

			for (Long roomId : roomsList)
			{
				// multiply by number for better comfort depend on number of beds
				double comfortPrice = 0.0;
				Room room =  Rooms.get(roomId);
				switch (room.getComfort())
				{
					case standardowy:
						comfortPrice = 1.0;
						break;
					case luksusowy:
						comfortPrice =  2.0;
						break;
					case rodzinny:
						comfortPrice = 1.5;
						break;
					case apartament:
						comfortPrice = 4.0;
						break;
				}

				oneDayPrice += (comfortPrice * room.getnOfBeds() * dailyPrice);
			}
			totalSum += (oneDayPrice * seasonFee);
		}

		//discount for frequent clients
		if (clientVisitCount > 2)
		{
			totalSum *= 0.9;
		}

		//discount for eariler reservation
		if (LocalDate.now().until(checkInDate).getDays() > 20)
		{
			totalSum *= 0.95;
		}

		return totalSum;
	}

	public Reservation checkReservation(long clientId, LocalDate checkInDate, LocalDate checkOutDate, long nOfBeds)
	{
		List<Long> roomList = getVacantRooms(checkInDate, checkOutDate);
		Map<Long, Room> roomMap = selectRooms(roomList, nOfBeds);
		List<Long> roomsIds = new ArrayList<>(roomMap.keySet());
		double totalPrice = calculateTotalPrice(checkInDate, checkOutDate, clientId, roomsIds);
		return new Reservation(reservationNumber++, checkInDate, checkOutDate, clientId, totalPrice, roomsIds);
	}

	public long addReservation(Reservation reservation)
	{
		Reservations.put(reservationNumber, reservation);
		Clients.get(reservation.getClientId()).incVisitCount();
		return reservationNumber++;
	}

	public void deleteReservation(long id)
	{
		Reservations.remove(id);
	}

	// SeasonalFee management
	public void loadSeasonalFees()
	{
		String line = "";
		String cvsSplitBy = ",";
		String seasonalFeePath = path + "seasonalfees.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(seasonalFeePath)))
		{
			while ((line = br.readLine()) != null)
			{
				String[] seasonalfeeParam = line.split(cvsSplitBy);
				if (seasonalfeeParam.length == 4)
				{
					SeasonalFee seasonalFee = new SeasonalFee(seasonalfeeParam[0], LocalDate.parse(seasonalfeeParam[1]), LocalDate.parse(seasonalfeeParam[2]), Double.parseDouble(seasonalfeeParam[3]));
					SeasonalFees.put(seasonalfeeParam[0], seasonalFee);
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveSeasonalFees()
	{
		StringBuilder builder = new StringBuilder();
		String seasonalFeePath = path + "seasonalfees.csv";

		try (FileWriter outFile = new FileWriter(seasonalFeePath))
		{
			for (SeasonalFee seasonalFee : SeasonalFees.values())
			{
				builder.append(seasonalFee.getEventName());
				builder.append(",");
				builder.append(seasonalFee.getStartDate());
				builder.append(",");
				builder.append(seasonalFee.getEndDate());
				builder.append(",");
				builder.append(seasonalFee.getFee());
				builder.append('\n');
			}
			outFile.write(builder.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void addSeasonalFee(String eventName, LocalDate startDate, LocalDate endDate, double fee)
	{
		SeasonalFee seasonalFee = new SeasonalFee(eventName, startDate, endDate, fee);
		SeasonalFees.put(eventName, seasonalFee);
	}

	public void deleteSeasonalFee(String eventName)
	{
		SeasonalFees.remove(eventName);
	}

	// Getters
	public Map<Long, Room> getRooms()
	{
		return Rooms;
	}
	public Map<Long, Client> getClients()
	{
		return Clients;
	}
	public Map<Long, Reservation> getReservations()
	{
		return Reservations;
	}
	public Map<String, SeasonalFee> getSeasonalFees()
	{
		return SeasonalFees;
	}
}