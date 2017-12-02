import java.util.HashMap;

class AdminPanelMenu extends BaseMenu
{
    public AdminPanelMenu()
    {
        menuActions = new HashMap<>();
        menuDescriptions = new HashMap<>();
        header = "LOGOWANIE";

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
            menuActions.put(9, AdminPanelMenu.class.getMethod("goBack"));
            menuActions.put(10, AdminPanelMenu.class.getMethod("exit"));

        }
        catch (Exception e)
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
        menuDescriptions.put(9, "Powrót");
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

    public void addRoom()
    {

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

    public void goBack()
    {

    }

    public void exit()
    {

    }
}
