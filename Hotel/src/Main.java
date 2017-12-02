import java.lang.reflect.Method;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		BaseMenu menu = new MainMenu();
		System.out.print("\033[H\033[2J");
		System.out.flush();

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		boolean condtition = true;

		while(condtition)
		{
			System.out.print(menu);
			int option = scanner.nextInt();

			try
			{
				Method method = menu.getAction(option);

				while (method == null)
				{
					System.out.println("Błędny numer opcji. Spróbuj ponownie: ");
					option = scanner.nextInt();
					method = menu.getAction(option);
				}

				menu = (BaseMenu) method.invoke(menu);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			System.out.print("\033[H\033[2J");
			System.out.flush();
		}

		/*Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");

		System.out.println("Witaj w systemie hotelu Relaks!");
		Hotel hotel = Hotel.getInstance();
		hotel.Init("out/production/Hotel/");

		long currentUserId = -1;
		boolean condtition = true;
		while (condtition)
		{
			System.out.println("[1] Zaloguj się do systemu");
			System.out.println("[2] Utwórz nowego użytkownika");
			Client client;
			int option = scanner.nextInt();
			boolean passError = false;
			switch (option)
			{
				case 1:
					System.out.println("[1] Panel recepcjonisty");
					System.out.println("[2] Panel klienta");
					int panel_option = scanner.nextInt();
					switch (panel_option)
					{
						case 1:
							System.out.printf("Podaj hasło:\n");
							String pass = scanner.next();

							if (hotel.isSystemPassCorrect(pass))
							{
								currentUserId = 1;
								condtition = false;
							}
							else
							{
								System.out.printf("Błędne hasło\n");
								passError = true;
							}
							break;
						case 2:
							System.out.printf("Podaj id:\n");
							currentUserId = scanner.nextLong();
							condtition = false;
							break;
						default:
							System.out.printf("wpisz jeden z poniższych numerów\n");
							break;
					}
					break;

				case 2:
					System.out.printf("Podaj imie:\n");
					String name = scanner.next();
					System.out.printf("Podaj nazwisko:\n");
					String surname = scanner.next();
					currentUserId = hotel.addClient(name, surname);
					condtition = false;
					break;
				default:
					System.out.printf("wpisz jeden z poniższych numerów\n");
					break;
			}
			client = hotel.getClients().get(currentUserId);
			if (client == null && !passError)
			{
				System.out.println("Nie ma klienta o takim id!");
				condtition = true;
			}
		}

		Client client = hotel.getClients().get(currentUserId);

		System.out.println("Zalogowano jako: " + client.toString());
		String currentUserStatus = client.getStatus();

		condtition = true;
		while (condtition)
		{
			System.out.println("[1] Aby wyszukać wolne pokoje");
			System.out.println("[2] Aby sprawdzić rezerwację");
			System.out.println("[3] Aby wyjść");
			if (currentUserStatus.equals("manager"))
			{
				System.out.println("[4] Aby dokonać rezerwacji na podany id");
				System.out.println("[5] Aby usunąć rezerwację o podanym id");
				System.out.println("[6] Aby dodać pokój");
				System.out.println("[7] Aby usunąć pokój");
				System.out.println("[8] Aby dodać użytkownika");
				System.out.println("[9] Aby usunąć użytkownika");
			}
			int option = scanner.nextInt();
			switch (option)
			{
				case 1:
					try
					{
						System.out.println("Podaj datę zameldowania w formacie dzień/miesiąc/rok");
						String checkInDate = scanner.next();
						System.out.println("Podaj datę wymeldowania w formacie dzień/miesiąc/rok");
						String checkOutDate = scanner.next();
						System.out.println("Podaj ilość łóżek");
						long nOfBeds = scanner.nextLong();
						LocalDate checkIn = dateInput(checkInDate);
						LocalDate checkOut = dateInput(checkOutDate);
						Reservation reservation = hotel.checkReservation(currentUserId, checkIn, checkOut, nOfBeds);
						if (reservation == null)
						{
							System.out.println("Nie znaleziono odpowiednich pokoi do zadanych kryteriów rezerwacji");
							break;
						}
						System.out.println("Proponowana rezerwacja:");
						System.out.println(reservation);
						System.out.println("[1] Aby dokonać powyższej rezerwacji");
						System.out.println("[2] Aby anulować powyższą rezerwację");
						int option2 = scanner.nextInt();
						switch (option2)
						{
							case 1:
								System.out.printf("Dokonano rezerwacji. Id rezerwacji: %d\n", hotel.addReservation(reservation));
								break;
							default:
								break;
						}
					}
					catch (InputMismatchException e)
					{
						System.out.println("Wpisano błędną daną");
					}
					catch (Exception e)
					{
						System.out.println("Wystąpił nastepujący błąd:"+ e.getMessage());
					}

					break;

				case 2:
					System.out.println("Podaj id rejestracji:");
					long reservationId = scanner.nextInt();
					if (hotel.getReservations().get(reservationId) != null)
					{
						System.out.println(hotel.getReservations().get(reservationId).toString());
					} else
					{
						System.out.println("Rezerwacja o takim id nie istnieje!");
					}
					break;
				case 3:

					condtition = false;
					break;

				case 4:
					if (!currentUserStatus.equals("manager"))
						break;
					try
					{
						System.out.println("Podaj id użytkownika, na którego chcesz dokonać rejestracji");
						long userId = scanner.nextLong();
						System.out.println("Podaj datę zameldowania w formacie dzień/miesiąc/rok");
						String checkInDate2 = scanner.next();
						System.out.println("Podaj datę wymeldowania w formacie dzień/miesiąc/rok");
						String checkOutDate2 = scanner.next();
						System.out.println("Podaj ilość łóżek");
						long nOfBeds2 = scanner.nextLong();
						Reservation reservation2 = hotel.checkReservation(userId, dateInput(checkInDate2), dateInput(checkOutDate2), nOfBeds2);
						if (reservation2 == null)
						{
							System.out.println("Nie znaleziono odpowiednich pokoi do zadanych kryteriów rezerwacji");
							break;
						}
						System.out.println("Proponowana rezerwacja:");
						System.out.println(reservation2);
						System.out.println("[1] Aby dokonać powyższej rezerwacji");
						System.out.println("[2] Aby anulować powyższą rezerwację");
						int option3 = scanner.nextInt();
						switch (option3)
						{
							case 1:
								System.out.printf("Dokonano rezerwacji. Id rezerwacji = %d\n", hotel.addReservation(reservation2));
								break;
							default:
								break;
						}
					}
					catch (Exception e)
					{
						System.out.println("Wpisano niepoprawną datę!");
					}
					break;

				case 5:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj id rezerwacji, którą chcesz usunąć:");
					long delReservationId = scanner.nextLong();
					hotel.deleteReservation(delReservationId);
					System.out.println("Rezerwacja prawidłowo usunięta");
					break;

				case 6:
					if (!currentUserStatus.equals("manager"))
						break;
					try
					{
						System.out.println("Podaj liczbę łóżek:");
						int n0fBeds = scanner.nextInt();
						System.out.println("Podaj opis pokoju:");
						String roomDescription = scanner.next();
						System.out.println("Podaj komfort pokoju (standardowy, rodzinny, apartament, luksusowy):");
						Comfort roomComfort = Comfort.valueOf(scanner.next());
						System.out.printf("Pomyślnie dodano pokój o id = %d\n", hotel.addRoom(n0fBeds, roomDescription, roomComfort));
					}
					catch (Exception e)
					{
						System.out.println("Nie ma takiego typu komfortu!");
					}
					break;

				case 7:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj numer pokoju, który chcesz usunąć:");
					long roomNr = scanner.nextLong();
					try
					{
						hotel.deleteRoom(roomNr);
					}
					catch (RoomInUse roomInUse)
					{
						System.out.printf(roomInUse.getMessage());
						break;
					}
					System.out.printf("Pomyślnie usunięto pokój nr = %d\n",roomNr);
					break;

				case 8:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.printf("Podaj imie:\n");
					String name = scanner.next();
					System.out.printf("Podaj nazwisko:\n");
					String surname = scanner.next();
					long newClientId = hotel.addClient(name, surname);
					System.out.printf("Utworzono użytkownika %s %s o id = %d",name, surname, newClientId);
					break;

				case 9:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj id użytkownika, którego chcesz usunąć:");
					long delUserId = scanner.nextLong();
					hotel.deleteClient(delUserId);
					System.out.printf("Pomyślnie usunięto użytkownika nr = %d",delUserId);
					break;
				case 10:
					for (Room room : hotel.getRooms().values()){
						System.out.println(room.toString());
					}
					break;
				case 11:
					for (Reservation reservation : hotel.getReservations().values()){
						System.out.println(reservation.toString());
					}
					break;
				case 12:
					for (Client client1 : hotel.getClients().values()){
						System.out.println(client1.toString());
					}
					break;
				case 13:
					for (SeasonalFee seasonalFee : hotel.getSeasonalFees().values()){
						System.out.println(seasonalFee.toString());
					}
					break;
			}
		}

		hotel.saveData();
	}

	// convert String to LocalDate
	public static LocalDate dateInput(String userInput)
	{

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
		LocalDate date = LocalDate.parse(userInput, dateFormat);
		return date;
	}*/
	}
}
