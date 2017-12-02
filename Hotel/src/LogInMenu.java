import java.util.HashMap;

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
            menuActions.put(2, LogInMenu.class.getMethod("logInAsUser"));
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

    public void logInAsAdmin()
    {

    }

    public void logInAsUser()
    {

    }

    public void goBack()
    {

    }

    public void exit()
    {

    }
}
