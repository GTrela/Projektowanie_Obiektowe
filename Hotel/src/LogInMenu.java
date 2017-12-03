import java.util.HashMap;
import java.util.Scanner;

class LogInMenu extends BaseMenu
{
    public LogInMenu()
    {
        menuActions = new HashMap<>();
        menuDescriptions = new HashMap<>();
        header = "LOGOWANIE";

        try
        {
            menuActions.put(1, LogInMenu.class.getMethod("logInAsAdmin"));
            menuActions.put(2, LogInMenu.class.getMethod("logInAsClient"));
            menuActions.put(3, LogInMenu.class.getMethod("goBack"));
            menuActions.put(4, LogInMenu.class.getMethod("exit"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        menuDescriptions.put(1, "Dostęp dla recepcjonisty");
        menuDescriptions.put(2, "Dostęp dla klienta");
        menuDescriptions.put(3, "Powrót");
        menuDescriptions.put(4, "Wyjście");
    }

    public BaseMenu logInAsAdmin()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        System.out.print("\nPodaj hasło: ");
        String password = scanner.next();
        int counter = 0;

        while (!hotel.isSystemPassCorrect(password) && counter < 4)
        {
            System.out.print("Błędne hasło, spróbuj ponownie: ");
            password = scanner.next();
            ++counter;
        }

        if (hotel.isSystemPassCorrect(password))
        {
            return new AdminPanelMenu();
        }
        else
        {
            return new MainMenu();
        }
    }

    public BaseMenu logInAsClient()
    {
        Hotel hotel = Hotel.getInstance();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.print("\nPodaj swój identyfikator: ");

        boolean correctInput = false;
        long clientID = 0;
        int counter = 0;

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
                    System.out.print("Błędny identyfikator, spróbuj ponownie: ");
                }
            }
            else
            {
                System.out.print("Błędny identyfikator, spróbuj ponownie: ");
                scanner.next();
            }

            ++counter;
        }
        while(!correctInput && counter < 4);

        if (hotel.getClients().get(clientID) != null)
        {
            currentUserID = clientID;
            return new ClientPanelMenu(currentUserID);
        }
        else
        {
            return new MainMenu();
        }
    }

    public BaseMenu goBack()
    {
        return new MainMenu();
    }

    public void exit()
    {
        System.exit(0);
    }
}
