import java.util.HashMap;

class ClientPanelMenu extends BaseMenu
{
    public ClientPanelMenu()
    {
        menuActions = new HashMap<>();
        menuDescriptions = new HashMap<>();
        header = "LOGOWANIE";

        try
        {
            menuActions.put(1, ClientPanelMenu.class.getMethod("logInAsAdmin"));
            menuActions.put(2, ClientPanelMenu.class.getMethod("logInAsUser"));
            menuActions.put(3, ClientPanelMenu.class.getMethod("goBack"));
            menuActions.put(4, ClientPanelMenu.class.getMethod("exit"));
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

    public void logInAsAdmin()
    {

    }

    public void logInAsUser()
    {

    }

    public BaseMenu goBack()
    {
        return new LogInMenu();
    }

    public void exit()
    {
        System.exit(0);
    }
}
