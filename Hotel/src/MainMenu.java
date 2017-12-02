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

        System.out.println("Został utworzony profil o ID: " + clientID);
        System.out.printf("Czy chcesz wrócić do menu głównego czy strony logowania? (1/2): ");
        int answer = scanner.nextInt();

        if (answer == 1)
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
        System.exit(0);
    }
}
