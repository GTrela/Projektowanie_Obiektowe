import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Witaj w systemie administracji hotelu Relaks!");
		System.out.println("Podaj ścieżkę do plików gdzie jest przechowywana konfiguracja:");

		String path = scanner.next();
		Hotel hotel = Hotel.getInstance();
		hotel.Init(path);

		long currentUserId = -1;
		boolean condtition = true;
		while (condtition)
		{
			System.out.println("[1] zaloguj się do systemu");
			System.out.println("[2] Utwórz nowego użytkownika");
			Client client;
			int option = scanner.nextInt();
			switch (option)
			{
				case 1:
					System.out.printf("Podaj id:\n");
					currentUserId = scanner.nextLong();
					condtition = false;
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
			if (client == null)
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
			System.out.println("[3] Aby zostać Recepcjonistą");
			System.out.println("[4] Aby wyjść");
			if (currentUserStatus.equals("manager"))
			{
				System.out.println("[5] Aby dokonać rezerwacji na podany id");
				System.out.println("[6] Aby usunąć rezerwację o podanym id");
				System.out.println("[7] Aby dodać pokój");
				System.out.println("[8] Aby usunąć pokój");
				System.out.println("[9] Aby dodać użytkownika");
				System.out.println("[10] Aby usunąć użytkownika");
			}
			int option = scanner.nextInt();
			switch (option)
			{
				case 1:
					System.out.println("Podaj datę zameldowania w formacie dzień/miesiąc/rok");
					String checkInDate = scanner.next();
					System.out.println("Podaj datę wymeldowania w formacie dzień/miesiąc/rok");
					String checkOutDate = scanner.next();
					System.out.println("Podaj ilość łóżek");
					long nOfBeds = scanner.nextLong();
					System.out.println("Proponowana rezerwacja:");
					Reservation reservation = hotel.checkReservation(currentUserId, dateInput(checkInDate), dateInput(checkOutDate), nOfBeds);
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
					client.setManager();
					System.out.println("Zostałeś menagerem! Wyjdź z systemu i zaloguj się ponownie!");
					break;
				case 4:
					condtition = false;
					break;
				case 5:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj id użytkownika, na którego chcesz dokonać rejestracji");
					long userId = scanner.nextLong();
					System.out.println("Podaj datę zameldowania w formacie dzień/miesiąc/rok");
					String checkInDate2 = scanner.next();
					System.out.println("Podaj datę wymeldowania w formacie dzień/miesiąc/rok");
					String checkOutDate2 = scanner.next();
					System.out.println("Podaj ilość łóżek");
					long nOfBeds2 = scanner.nextLong();
					System.out.println("Proponowana rezerwacja:");
					Reservation reservation2 = hotel.checkReservation(userId, dateInput(checkInDate2), dateInput(checkOutDate2), nOfBeds2);
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
					break;
				case 6:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj id rezerwacji, którą chcesz usunąć:");
					long delReservationId = scanner.nextLong();
					hotel.deleteReservation(delReservationId);
					System.out.println("Rezerwacja prawidłowo usunięta");
					break;
				case 7:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj liczbę łóżek:");
					long n0fBeds = scanner.nextLong();
					System.out.println("Podaj opis pokoju:");
					String roomDescription = scanner.next();
					System.out.println("Podaj komfort pokoju:");
					Comfort roomComfort = Comfort.valueOf(scanner.next());
					System.out.printf("Pomyślnie dodano pokój o id = %d\n",hotel.addRoom(n0fBeds,roomDescription,roomComfort));
					break;
				case 8:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj numer pokoju, który chcesz usunąć:");
					long roomNr = scanner.nextLong();
					hotel.deleteRoom(roomNr);
					System.out.printf("Pomyślnie usunięto pokój nr = %d",roomNr);
					break;
				case 9:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.printf("Podaj imie:\n");
					String name = scanner.next();
					System.out.printf("Podaj nazwisko:\n");
					String surname = scanner.next();
					long newClientId = hotel.addClient(name, surname);
					System.out.printf("Utworzono użytkownika %s %s o id = %d",name, surname, newClientId);
					break;
				case 10:
					if (!currentUserStatus.equals("manager"))
						break;
					System.out.println("Podaj id użytkownika, którego chcesz usunąć:");
					long delUserId = scanner.nextLong();
					hotel.deleteClient(delUserId);
					System.out.printf("Pomyślnie usunięto użytkownika nr = %d",delUserId);
					break;
			}
		}

		hotel.saveData();
	}

	// convert String to LocalDate
	public static LocalDate dateInput(String userInput)
	{

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yy");
		LocalDate date = LocalDate.parse(userInput, dateFormat);
		return date;
	}
}
