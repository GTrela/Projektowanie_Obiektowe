import java.util.HashMap;
import java.util.Scanner;

class MainMenu extends BaseMenu
{
    public MainMenu()
    {
        header = "MENU GŁÓWNE";
        menuActions = new HashMap<>();
        menuDescriptions = new HashMap<>();

        try
        {
            menuActions.put(1, MainMenu.class.getMethod("logIn"));
            menuActions.put(2, MainMenu.class.getMethod("createNewUser"));
            menuActions.put(3, MainMenu.class.getMethod("exit"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        menuDescriptions.put(1, "Logowanie");
        menuDescriptions.put(2, "Rejestracja");
        menuDescriptions.put(3, "Wyjście");
    }

    public BaseMenu logIn()
    {
        return new LogInMenu();
    }

    public BaseMenu createNewUser()
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
        System.out.printf("Powrót do menu głównego [1] czy strony logowania [2]? ");

        boolean correctInput = false;
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
            return new MainMenu();
        }
        else
        {
            return new LogInMenu();
        }
    }

    public void exit()
    {
        Hotel hotel = Hotel.getInstance();
        hotel.saveData();
        System.exit(0);
    }
}
