import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

class ClientPanelMenu extends BaseMenu
{
    public ClientPanelMenu(long currentUserID)
    {
        this.currentUserID = currentUserID;
        menuActions = new HashMap<>();
        menuDescriptions = new HashMap<>();
        Hotel hotel = Hotel.getInstance();
        header = "PANEL KLIENTA (" + hotel.getClients().get(currentUserID).getName()
                + " " + hotel.getClients().get(currentUserID).getSurname() + ")";

        try
        {
            menuActions.put(1, ClientPanelMenu.class.getMethod("findVacantRooms"));
            menuActions.put(2, ClientPanelMenu.class.getMethod("showReservations"));
            menuActions.put(3, ClientPanelMenu.class.getMethod("logout"));
            menuActions.put(4, ClientPanelMenu.class.getMethod("exit"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        menuDescriptions.put(1, "Wyszukaj wolne pokoje");
        menuDescriptions.put(2, "Wyświetl swoje rezerwacje");
        menuDescriptions.put(3, "Wyloguj");
        menuDescriptions.put(4, "Wyjście");
    }

    public BaseMenu findVacantRooms()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String checkInDate = "";
        String checkOutDate = "";
        int nOfBeds = -1;
        boolean correctInput = false;

        try
        {
            System.out.print("\nPodaj datę zameldowania w formacie dzień/miesiąc/rok: ");
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
            reservation = hotel.checkReservation(currentUserID, checkIn, checkOut, nOfBeds);
        }
        catch (NoVacantRooms e1)
        {
            System.out.println("\nBrak wolnych pokoi.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new ClientPanelMenu(currentUserID);
        }
        catch (NotEnoughBeds e2)
        {
            System.out.println("\nBrak wystarczającej ilości łóżek.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new ClientPanelMenu(currentUserID);
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
                    " dla użytkownika o ID: " + currentUserID + " została dodana.");
            System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...");

            scanner.next();

            return new ClientPanelMenu(currentUserID);
        }
        else
        {
            scanner.nextLine();
            return new ClientPanelMenu(currentUserID);
        }
    }

    public BaseMenu showReservations()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        boolean noReservations = true;

        for (Reservation reservation : hotel.getReservations().values())
        {
            if (reservation.getClientId() == currentUserID)
            {
                noReservations = false;
                System.out.println("\n| Rezerwacja o ID: " + reservation.getId()
                        + " (Klient: " + hotel.getClients().get(reservation.getClientId()).getName()
                        + " " + hotel.getClients().get(reservation.getClientId()).getSurname() + ") |");
                System.out.print(reservation);
                System.out.println("----------------------------------------------------");
            }
        }

        if (noReservations)
        {
            System.out.println("\nBrak rezerwacji.");
        }

        System.out.printf("\nNaciśnij ENTER, aby powrócić do głównej strony panelu...\n");

        scanner.nextLine();

        return new ClientPanelMenu(currentUserID);
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
