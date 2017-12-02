import java.util.HashMap;

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

    public LogInMenu logIn()
    {
        return new LogInMenu();
    }

    public void createNewUser()
    {
    }

    public void exit()
    {
    }
}
