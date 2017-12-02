import java.util.HashMap;

class ClientPanelMenu extends BaseMenu
{
    public ClientPanelMenu()
    {
        menuActions = new HashMap<>();
        menuDescriptions = new HashMap<>();
        header = "PANEL KLIENTA";

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
