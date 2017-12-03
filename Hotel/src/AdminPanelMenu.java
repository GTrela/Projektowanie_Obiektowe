import java.util.HashMap;
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
            menuActions.put(2, AdminPanelMenu.class.getMethod("checkReservation"));
            menuActions.put(3, AdminPanelMenu.class.getMethod("addReservation"));
            menuActions.put(4, AdminPanelMenu.class.getMethod("removeReservation"));
            menuActions.put(5, AdminPanelMenu.class.getMethod("addRoom"));
            menuActions.put(6, AdminPanelMenu.class.getMethod("removeRoom"));
            menuActions.put(7, AdminPanelMenu.class.getMethod("addUser"));
            menuActions.put(8, AdminPanelMenu.class.getMethod("removeUser"));
            menuActions.put(9, AdminPanelMenu.class.getMethod("logout"));
            menuActions.put(10, AdminPanelMenu.class.getMethod("exit"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        menuDescriptions.put(1, "Wyszukaj wolne pokoje");
        menuDescriptions.put(2, "Sprawdź rezerwację");
        menuDescriptions.put(3, "Dodaj rezerwację");
        menuDescriptions.put(4, "Usuń rezerwację");
        menuDescriptions.put(5, "Dodaj pokój");
        menuDescriptions.put(6, "Usuń pokój");
        menuDescriptions.put(7, "Dodaj użytkownika");
        menuDescriptions.put(8, "Usuń użytkownika");
        menuDescriptions.put(9, "Wyloguj");
        menuDescriptions.put(10, "Wyjście");
    }

    public void findVacantRooms()
    {

    }

    public void checkReservation()
    {
    }

    public void addReservation()
    {

    }

    public void removeReservation()
    {

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
        hotel.getRooms();

        return new AdminPanelMenu();
    }

    public void removeRoom()
    {

    }

    public void addUser()
    {

    }

    public void removeUser()
    {

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