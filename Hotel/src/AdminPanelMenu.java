import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class AdminPanelMenu extends BaseMenu
{
    public AdminPanelMenu()
    {
        menuActions = new HashMap<>();
        menuDescriptions = new HashMap<>();
        header = "PANEL RECEPCJONISTY";

        try
        {
            menuActions.put(1, AdminPanelMenu.class.getMethod("findVacantRooms"));
            menuActions.put(2, AdminPanelMenu.class.getMethod("showReservation"));
            menuActions.put(3, AdminPanelMenu.class.getMethod("showReservations"));
            menuActions.put(4, AdminPanelMenu.class.getMethod("addReservation"));
            menuActions.put(5, AdminPanelMenu.class.getMethod("deleteReservation"));
            menuActions.put(6, AdminPanelMenu.class.getMethod("showSeasonalFees"));
            menuActions.put(7, AdminPanelMenu.class.getMethod("addSeasonalFee"));
            menuActions.put(8, AdminPanelMenu.class.getMethod("deleteSeasonalFee"));
            menuActions.put(9, AdminPanelMenu.class.getMethod("showRooms"));
            menuActions.put(10, AdminPanelMenu.class.getMethod("addRoom"));
            menuActions.put(11, AdminPanelMenu.class.getMethod("deleteRoom"));
            menuActions.put(12, AdminPanelMenu.class.getMethod("showUsers"));
            menuActions.put(13, AdminPanelMenu.class.getMethod("addUser"));
            menuActions.put(14, AdminPanelMenu.class.getMethod("deleteUser"));
            menuActions.put(15, AdminPanelMenu.class.getMethod("logout"));
            menuActions.put(16, AdminPanelMenu.class.getMethod("exit"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        menuDescriptions.put(1, "Wyszukaj wolne pokoje");
        menuDescriptions.put(2, "Sprawdź rezerwację"); //
        menuDescriptions.put(3, "Wyświetl rezerwacje"); //
        menuDescriptions.put(4, "Dodaj rezerwację");
        menuDescriptions.put(5, "Usuń rezerwację");
        menuDescriptions.put(6, "Wyświetl okresy specjalne");
        menuDescriptions.put(7, "Dodaj okres specjalny");
        menuDescriptions.put(8, "Usuń okres specjalny");
        menuDescriptions.put(9, "Wyświetl pokoje");
        menuDescriptions.put(10, "Dodaj pokój");
        menuDescriptions.put(11, "Usuń pokój");
        menuDescriptions.put(12, "Wyświetl użytkowników");
        menuDescriptions.put(13, "Dodaj użytkownika");
        menuDescriptions.put(14, "Usuń użytkownika");
        menuDescriptions.put(15, "Wyloguj");
        menuDescriptions.put(16, "Wyjście");
    }

    public BaseMenu findVacantRooms()
    {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String checkInDate = "";
        String checkOutDate = "";
        int nOfBeds = -1;

        try
        {
            System.out.print("Podaj datę zameldowania w formacie dzień/miesiąc/rok: ");
            boolean correctInput = false;

            do
            {
                checkInDate = scanner.nextLine();

                if (this.dateInput(checkInDate) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędny format daty, spróbuj ponownie: ");
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.print("Podaj datę wymeldowania w formacie dzień/miesiąc/rok: ");
            boolean correctInput = false;

            do
            {
                checkOutDate = scanner.nextLine();

                if (this.dateInput(checkOutDate) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędny format daty, spróbuj ponownie: ");
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.print("Podaj ilość łóżek: ");
        boolean correctInput = false;

        do
        {
            if (scanner.hasNextInt())
            {
                nOfBeds = scanner.nextInt();

                if (nOfBeds > 0)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędna ilość łóżek, spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Błędna ilość łóżek, spróbuj ponownie: ");
                scanner.next();
            }
        }
        while(!correctInput);

        Hotel hotel = Hotel.getInstance();
        LocalDate checkIn = dateInput(checkInDate);
        LocalDate checkOut = dateInput(checkOutDate);
        List<Long> roomList;
        Map<Long, Room> roomMap;

        try
        {
            roomList = hotel.getVacantRooms(checkIn, checkOut);
            roomMap = hotel.selectRooms(roomList, nOfBeds);
        }
        catch (NoVacantRooms e1)
        {
            System.out.println("Brak wolnych pokoi.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new AdminPanelMenu();
        }
        catch (NotEnoughBeds e2)
        {
            System.out.println("Brak wystarczającej ilości łóżek.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new AdminPanelMenu();
        }

        System.out.println();
        System.out.printf("%-8s%-8s%-15s%-40s\n","Numer","Łóżka","Standard","Opis");

        for (Room room : roomMap.values())
        {
            System.out.println(room);
        }

        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.next();

        return new AdminPanelMenu();
    }

    public void showReservation()
    {

    }

    public void showReservations()
    {

    }

    public BaseMenu addReservation()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String checkInDate = "";
        String checkOutDate = "";
        int nOfBeds = -1;
        long clientID = 0;

        System.out.print("\nPodaj identyfikator klienta: ");

        boolean correctInput = false;

        do
        {
            if (scanner.hasNextLong())
            {
                clientID = scanner.nextLong();

                if (hotel.getClients().get(clientID) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Klient o takim ID nie istnieje, spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Klient o takim ID nie istnieje, spróbuj ponownie: ");
                scanner.next();
            }
        }
        while(!correctInput);

        try
        {
            System.out.print("Podaj datę zameldowania w formacie dzień/miesiąc/rok: ");
            correctInput = false;

            do
            {
                checkInDate = scanner.next();

                if (this.dateInput(checkInDate) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędny format daty, spróbuj ponownie: ");
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.print("Podaj datę wymeldowania w formacie dzień/miesiąc/rok: ");
            correctInput = false;

            do
            {
                checkOutDate = scanner.next();

                if (this.dateInput(checkOutDate) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędny format daty, spróbuj ponownie: ");
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.print("Podaj ilość łóżek: ");
        correctInput = false;

        do
        {
            if (scanner.hasNextInt())
            {
                nOfBeds = scanner.nextInt();

                if (nOfBeds > 0)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędna ilość łóżek, spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Błędna ilość łóżek, spróbuj ponownie: ");
                scanner.next();
            }
        }
        while(!correctInput);

        LocalDate checkIn = dateInput(checkInDate);
        LocalDate checkOut = dateInput(checkOutDate);
        Reservation reservation;

        try
        {
            reservation = hotel.checkReservation(clientID, checkIn, checkOut, nOfBeds);
        }
        catch (NoVacantRooms e1)
        {
            System.out.println("Brak wolnych pokoi.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new AdminPanelMenu();
        }
        catch (NotEnoughBeds e2)
        {
            System.out.println("Brak wystarczającej ilości łóżek.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new AdminPanelMenu();
        }

        System.out.println("\nProponowana rezerwacja: ");
        System.out.println(reservation);

        System.out.printf("Dokonać rezerwacji [1] czy wrócić do głównej strony panelu [2]? ");

        correctInput = false;
        int option = 0;

        do
        {
            if (scanner.hasNextInt())
            {
                option = scanner.nextInt();

                if (option == 1 || option == 2)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędny numer opcji. Spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Błędny numer opcji. Spróbuj ponownie: ");
                scanner.next();
            }
        }
        while(!correctInput);

        if (option == 1)
        {
            hotel.addReservation(reservation);

            System.out.println("\nRezerwacja o ID: " + reservation.getId() +
                    " dla użytkownika o ID: " + clientID + " została dodana.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new AdminPanelMenu();
        }
        else
        {
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new AdminPanelMenu();
        }
    }

    public BaseMenu deleteReservation()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.print("\nPodaj ID rezerwacji: ");

        boolean correctInput = false;
        long reservationID = 0;

        do
        {
            if (scanner.hasNextLong())
            {
                reservationID = scanner.nextLong();

                if (hotel.getReservations().get(reservationID) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Rezerwacja o takim ID nie istnieje, spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Rezerwacja o takim ID nie istnieje, spróbuj ponownie: ");
                scanner.next();
            }
        }
        while(!correctInput);

        hotel.deleteReservation(reservationID);
        System.out.println("\nRezerwacja o ID " + reservationID + " została usunięta.");
        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.next();

        return new AdminPanelMenu();
    }

    public BaseMenu showSeasonalFees()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.printf("%-20s%-15s%-15s%-12s\n","Nazwa","Początek","Koniec","Stawka");

        for (SeasonalFee seasonalFee : hotel.getSeasonalFees().values())
        {
            System.out.println(seasonalFee);
        }

        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.nextLine();

        return new AdminPanelMenu();
    }

    public BaseMenu addSeasonalFee()
    {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String eventName = "";
        String startDate = "";
        String endDate = "";
        double fee = -1;

        try
        {
            System.out.print("Podaj nazwę okresu specjalnego: ");
            eventName = scanner.nextLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.print("Podaj datę początkową w formacie dzień/miesiąc/rok: ");
            boolean correctInput = false;

            do
            {
                startDate = scanner.nextLine();

                if (this.dateInput(startDate) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędny format daty, spróbuj ponownie: ");
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.print("Podaj datę końcową w formacie dzień/miesiąc/rok: ");
            boolean correctInput = false;

            do
            {
                endDate = scanner.nextLine();

                if (this.dateInput(endDate) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Błędny format daty, spróbuj ponownie: ");
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.print("Podaj stawkę (format: 0,00): ");
            boolean correctInput = false;

            do
            {
                if (scanner.hasNextDouble())
                {
                    fee = scanner.nextDouble();

                    if (fee > 0)
                    {
                        correctInput = true;
                    }
                    else
                    {
                        System.out.print("Błędny format stawki. Spróbuj ponownie: ");
                    }
                }
                else
                {
                    System.out.print("Błędna format stawki. Spróbuj ponownie: ");
                    scanner.next();
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Hotel hotel = Hotel.getInstance();
        LocalDate start = dateInput(startDate);
        LocalDate end = dateInput(endDate);

        hotel.addSeasonalFee(eventName,start,end,fee);

        System.out.println("\nZostał dodany okres specjalny o nazwie: " + eventName);
        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.next();

        return new AdminPanelMenu();
    }

    public BaseMenu deleteSeasonalFee()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.print("\nPodaj nazwę okresu specjalnego: ");
        boolean correctInput = false;
        String eventName = "";

        do
        {
            eventName = scanner.nextLine();

            if (hotel.getSeasonalFees().get(eventName) != null)
            {
                correctInput = true;
            }
            else
            {
                System.out.print("Okres specjalny o takiej nazwie nie istnieje, spróbuj ponownie: ");
            }
        }
        while(!correctInput);

        hotel.deleteSeasonalFee(eventName);

        System.out.println("\nOkres specjalny o nazwie \"" + eventName + "\" został usunięty.");
        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.nextLine();

        return new AdminPanelMenu();
    }

    public BaseMenu showRooms()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.printf("%-8s%-8s%-15s%-40s\n","Numer","Łóżka","Standard","Opis");

        for (Room room : hotel.getRooms().values())
        {
            System.out.println(room);
        }

        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.nextLine();

        return new AdminPanelMenu();
    }

    public BaseMenu addRoom()
    {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        int numberOfBeds = -1;
        String description = "";
        String roomComfort = "";

        try
        {
            System.out.print("\nPodaj ilość łóżek: ");
            boolean correctInput = false;

            do
            {
                if (scanner.hasNextInt())
                {
                    numberOfBeds = scanner.nextInt();

                    if (numberOfBeds > 0)
                    {
                        correctInput = true;
                    }
                    else
                    {
                        System.out.print("Błędna ilość łóżek. Spróbuj ponownie: ");
                    }
                }
                else
                {
                    System.out.print("Błędna ilość łóżek. Spróbuj ponownie: ");
                    scanner.next();
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.print("Podaj opis pokoju: ");
            description = scanner.nextLine();
            description += scanner.nextLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.print("Podaj komfort pokoju (standardowy, rodzinny, apartament, luksusowy): ");
            boolean correctInput = false;

            do
            {
                roomComfort = scanner.next();

                for (Comfort c : Comfort.values())
                {
                    if (c.name().equals(roomComfort))
                    {
                        correctInput = true;
                    }
                }

                if (!correctInput)
                {
                    System.out.print("Błędna nazwa, spróbuj ponownie: ");
                }
            }
            while(!correctInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Hotel hotel = Hotel.getInstance();
        long roomID = hotel.addRoom(numberOfBeds, description, Comfort.valueOf(roomComfort));

        System.out.println("\nZostał dodany pokój o ID: " + roomID);
        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.next();

        return new AdminPanelMenu();
    }

    public BaseMenu deleteRoom()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.print("\nPodaj numer: ");

        boolean correctInput = false;
        long roomNr = 0;

        do
        {
            if (scanner.hasNextLong())
            {
                roomNr = scanner.nextLong();

                if (hotel.getRooms().get(roomNr) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Pokój o takim numerze nie istnieje, spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Pokój o takim numerze nie istnieje, spróbuj ponownie: ");
                scanner.next();
            }
        }
        while(!correctInput);

        try
        {
            hotel.deleteRoom(roomNr);
            System.out.println("\nPokój o numerze " + roomNr + " został usunięty.");
        }
        catch (RoomInUse e)
        {
            System.out.println("Pokój nie może zostać usunięty - jest zarezerwowany.");
        }

        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.next();

        return new AdminPanelMenu();
    }

    public BaseMenu showUsers()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.printf("%-5s%-15s%-15s%-10s\n","ID","Imię","Nazwisko","Wizyty");

        for (Client client : hotel.getClients().values())
        {
            System.out.println(client);
        }

        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.nextLine();

        return new AdminPanelMenu();
    }

    public BaseMenu addUser()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.printf("\nPodaj imię: ");
        String name = scanner.next();

        System.out.printf("Podaj nazwisko: ");
        String surname = scanner.next();

        long clientID = hotel.addClient(name, surname);

        System.out.println("\nZostał utworzony profil o ID: " + clientID);
        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.next();

        return new AdminPanelMenu();
    }

    public BaseMenu deleteUser()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.print("\nPodaj identyfikator: ");

        boolean correctInput = false;
        long clientID = 0;

        do
        {
            if (scanner.hasNextLong())
            {
                clientID = scanner.nextLong();

                if (hotel.getClients().get(clientID) != null)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.print("Klient o takim ID nie istnieje, spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Klient o takim ID nie istnieje, spróbuj ponownie: ");
                scanner.next();
            }
        }
        while(!correctInput);

        hotel.deleteClient(clientID);

        System.out.println("\nUżytkownik o ID " + clientID + " został usunięty.");
        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

        scanner.next();

        return new AdminPanelMenu();
    }

    public BaseMenu logout()
    {
        return new LogInMenu();
    }

    public void exit()
    {
        Hotel hotel = Hotel.getInstance();
        hotel.saveData();
        System.exit(0);
    }
}
