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
            menuActions.put(2, ClientPanelMenu.class.getMethod("checkReservation"));
            menuActions.put(3, ClientPanelMenu.class.getMethod("logout"));
            menuActions.put(4, ClientPanelMenu.class.getMethod("exit"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        menuDescriptions.put(1, "Wyszukaj puste pokoje");
        menuDescriptions.put(2, "Sprawdź rezerwację");
        menuDescriptions.put(3, "Wyloguj");
        menuDescriptions.put(4, "Wyjście");
    }

    public void findVacantRooms()
    {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String checkInDate = "";
        String checkOutDate = "";
        int numberOfBeds = -1;
        Reservation reservation = null;

        try
        {
            System.out.print("Podaj datę zameldowania w formacie dzień/miesiąc/rok: ");
            boolean correctInput = false;

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
            System.out.print("Podaj datę wymeldowania w formacie dzień/miesiąc/rok: ");
            boolean correctInput = false;

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
            System.out.print("Podaj ilość łóżek: ");
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

        Hotel hotel = Hotel.getInstance();
        LocalDate checkIn = dateInput(checkInDate);
        LocalDate checkOut = dateInput(checkOutDate);

        try
        {
            reservation = hotel.checkReservation(currentUserID, checkIn, checkOut, numberOfBeds);
        }
        catch (NotEnoughBeds e1)
        {
            System.out.println("Brak wystarczającej ilości łóżek.");
        }
        catch (NoVacantRooms e2)
        {
            System.out.println("Brak wystarczającej ilości pokoi");
        }

        if (reservation == null)
        {
            System.out.println("Nie odnaleziono pokoi odpowiadających podanym kryteriom.");
        }

        System.out.println("Proponowana rezerwacja: ");
        System.out.println(reservation);

        //this.dateInput("2/10/2017");
    }

    public void checkReservation()
    {

    }

    public BaseMenu logout()
    {
        return new LogInMenu();
    }

    public void exit()
    {
        System.exit(0);
    }
}
